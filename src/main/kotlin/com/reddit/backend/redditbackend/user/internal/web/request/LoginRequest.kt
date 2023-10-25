package com.reddit.backend.redditbackend.user.internal.web.request

import com.reddit.backend.redditbackend.user.internal.dto.LoginDto


data class LoginRequest(
    val username: String,
    val password: String
)

fun LoginRequest.toDto() = LoginDto(username, password)