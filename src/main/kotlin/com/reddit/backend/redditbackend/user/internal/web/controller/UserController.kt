package com.reddit.backend.redditbackend.user.internal.web.controller

import com.reddit.backend.redditbackend.user.internal.service.UserService
import com.reddit.backend.redditbackend.user.internal.web.request.CreateUserRequest
import com.reddit.backend.redditbackend.user.internal.web.request.LoginRequest
import com.reddit.backend.redditbackend.user.internal.web.request.toDto
import com.reddit.backend.redditbackend.user.internal.web.response.UserResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
@Tag(name = "User Controller")
class UserController(
    private val userService: UserService
) {

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): UserResponse =
        userService.getUserById(id)

    @PostMapping
    fun createUser(@RequestBody createUserRequest: CreateUserRequest): UserResponse =
        userService.createUser(createUserRequest.toDto())

    @PostMapping("/login")
    fun loginUser(@RequestBody loginRequest: LoginRequest): UserResponse =
        userService.loginUser(loginRequest.toDto())

}