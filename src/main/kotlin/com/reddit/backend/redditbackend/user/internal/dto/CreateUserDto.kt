package com.reddit.backend.redditbackend.user.internal.dto

import com.reddit.backend.redditbackend.core.domain.entity.UserEntity

data class CreateUserDto(
    val username: String,
    var password: String,
    val email: String
)

fun CreateUserDto.toEntity() = UserEntity(username = username, password = password, email = email)