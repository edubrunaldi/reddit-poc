package com.reddit.backend.redditbackend.user.internal.service

import com.reddit.backend.redditbackend.core.domain.repository.UserRepository
import com.reddit.backend.redditbackend.user.internal.dto.CreateUserDto
import com.reddit.backend.redditbackend.user.internal.dto.LoginDto
import com.reddit.backend.redditbackend.user.internal.dto.toEntity
import com.reddit.backend.redditbackend.user.internal.exception.EmailExistsException
import com.reddit.backend.redditbackend.user.internal.exception.PasswordMissMatchException
import com.reddit.backend.redditbackend.user.internal.exception.UserNotFoundException
import com.reddit.backend.redditbackend.user.internal.exception.UsernameExistsException
import com.reddit.backend.redditbackend.user.internal.exception.InvalidPasswordException
import com.reddit.backend.redditbackend.user.internal.extension.toUserResponse
import com.reddit.backend.redditbackend.user.internal.web.response.UserResponse
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


    private fun validateUser(createUserDto: CreateUserDto) =
        validateUsername(createUserDto.username)
            .also { validateEmail(createUserDto.email) }
            .also { validatePasswordCriteria(createUserDto.password) }

    private fun validatePasswordCriteria(password: String) {
        passwordService.validatePasswordCriteria(password)
            .takeUnless { it }
            ?.run { throw InvalidPasswordException() }
    }


    private fun validateEmail(email: String) =
        userRepository.existsByEmail(email)
            .takeIf { it }
            ?.run { throw EmailExistsException(email) }

    private fun validateUsername(username: String) =
        userRepository.existsByUsername(username)
            .takeIf { it }
            ?.run { throw UsernameExistsException(username) }

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
