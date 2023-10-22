package com.reddit.backend.redditbackend.user.service

import com.reddit.backend.redditbackend.user.domain.entity.toUserResponse
import com.reddit.backend.redditbackend.user.domain.repository.UserRepository
import com.reddit.backend.redditbackend.user.dto.CreateUserDto
import com.reddit.backend.redditbackend.user.dto.toEntity
import com.reddit.backend.redditbackend.user.web.response.UserResponse
import org.springframework.stereotype.Service

@Service
class CreateUserService(
    private val passwordService: PasswordService,
    private val userRepository: UserRepository
) {

    fun createUser(createUserDto: CreateUserDto): UserResponse =
        createUserDto
            .apply { password = passwordService.hashPassword(password) }
            .toEntity()
            .let { userRepository.save(it) }
            .toUserResponse()

}
