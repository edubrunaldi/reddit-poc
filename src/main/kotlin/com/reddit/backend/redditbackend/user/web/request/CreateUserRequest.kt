package com.reddit.backend.redditbackend.user.web.request

import com.reddit.backend.redditbackend.user.dto.CreateUserDto


data class CreateUserRequest(
    val username: String,
    val password: String,
    val email: String
)

fun CreateUserRequest.toDto() = CreateUserDto(username, password, email)