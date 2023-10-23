package com.reddit.backend.redditbackend.user.service

import com.reddit.backend.redditbackend.user.domain.entity.toUserResponse
import com.reddit.backend.redditbackend.user.domain.repository.UserRepository
import com.reddit.backend.redditbackend.user.dto.CreateUserDto
import com.reddit.backend.redditbackend.user.dto.LoginDto
import com.reddit.backend.redditbackend.user.dto.toEntity
import com.reddit.backend.redditbackend.user.exception.EmailExistsException
import com.reddit.backend.redditbackend.user.exception.PasswordMissMatchException
import com.reddit.backend.redditbackend.user.exception.UserNotFoundException
import com.reddit.backend.redditbackend.user.exception.UsernameExistsException
import com.reddit.backend.redditbackend.user.web.response.UserResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService(
    private val passwordService: PasswordService,
    private val userRepository: UserRepository
) {

    fun createUser(createUserDto: CreateUserDto): UserResponse =
        createUserDto
            .also { validateUser(createUserDto) }
            .apply { password = passwordService.hashPassword(password) }
            .toEntity()
            .let { userRepository.save(it) }
            .toUserResponse()

    fun loginUser(loginDto: LoginDto): UserResponse =
        userRepository.findByUsername(loginDto.username)
            .takeIf { it != null }
            ?.also { validatePassword(loginDto.password, it.password) }
            ?.toUserResponse()
            ?: throw UserNotFoundException()


    private fun validateUser(createUserDto: CreateUserDto): Unit =
        validateUsername(createUserDto.username)
            .run { validateEmail(createUserDto.email) }
            .run { passwordService.validatePasswordCriteria(createUserDto.password) }


    private fun validateEmail(email: String): Unit =
        userRepository.existsByUsername(email)
            .takeUnless { it }
            .run { throw EmailExistsException(email) }

    private fun validateUsername(username: String): Unit =
        userRepository.existsByUsername(username)
            .takeUnless { it }
            .run { throw UsernameExistsException(username) }

    private fun validatePassword(password: String, passwordSaved: String) {
        if (passwordService.hashPassword(password) != passwordSaved) {
            throw PasswordMissMatchException()
        }
    }

    fun getUserById(userId: Long): UserResponse =
        userRepository.findByIdOrNull(userId)
            ?.toUserResponse()
            ?: throw UserNotFoundException()

}
