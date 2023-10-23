package com.reddit.backend.redditbackend.user.web.controller

import com.reddit.backend.redditbackend.user.service.UserService
import com.reddit.backend.redditbackend.user.web.request.CreateUserRequest
import com.reddit.backend.redditbackend.user.web.request.LoginRequest
import com.reddit.backend.redditbackend.user.web.request.toDto
import com.reddit.backend.redditbackend.user.web.response.UserResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
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