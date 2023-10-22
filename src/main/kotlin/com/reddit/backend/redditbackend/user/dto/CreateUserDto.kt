package com.reddit.backend.redditbackend.user.dto

import com.reddit.backend.redditbackend.user.domain.entity.UserEntity

data class CreateUserDto(
    val username: String,
    var password: String,
    val email: String
)

fun CreateUserDto.toEntity() = UserEntity(username = username, password = password, email = email)