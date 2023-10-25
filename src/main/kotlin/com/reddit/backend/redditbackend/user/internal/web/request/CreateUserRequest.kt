package com.reddit.backend.redditbackend.user.internal.web.request

import com.reddit.backend.redditbackend.user.internal.dto.CreateUserDto


data class CreateUserRequest(
    val username: String,
    val password: String,
    val email: String
)

fun CreateUserRequest.toDto() = CreateUserDto(username, password, email)