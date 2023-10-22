package com.reddit.backend.redditbackend.user.web.controller

import com.reddit.backend.redditbackend.user.service.CreateUserService
import com.reddit.backend.redditbackend.user.web.request.CreateUserRequest
import com.reddit.backend.redditbackend.user.web.request.toDto
import com.reddit.backend.redditbackend.user.web.response.UserResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(private val createUserService: CreateUserService) {

//    @GetMapping("/{id}")
//    fun getUserById(@PathVariable id: Long): User = userRepository.findById(id).orElseThrow()

    @PostMapping
    fun createUser(@RequestBody createUserRequest: CreateUserRequest): UserResponse =
        createUserService.createUser(createUserRequest.toDto())

//    @PostMapping("/login")
//    fun loginUser(@RequestBody loginRequest: LoginRequest): UserLoginResponse {
//        val user = userRepository.findByEmail(loginRequest.email).orElseThrow()
//        if (user.password == loginRequest.password) {
//            return UserLoginResponse(user.id)
//        } else {
//            throw AuthenticationException("Invalid password")
//        }
//    }
}