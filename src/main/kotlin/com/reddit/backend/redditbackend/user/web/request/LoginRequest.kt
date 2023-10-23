package com.reddit.backend.redditbackend.user.web.request

import com.reddit.backend.redditbackend.user.dto.LoginDto


data class LoginRequest(
    val username: String,
    val password: String
)

fun LoginRequest.toDto() = LoginDto(username, password)