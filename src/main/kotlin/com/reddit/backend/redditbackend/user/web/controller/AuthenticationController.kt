package com.reddit.backend.redditbackend.user.web.controller

import com.reddit.backend.redditbackend.user.web.request.SignUpRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("authentication")
class AuthenticationController {

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    fun signUp(@RequestBody signUpRequest: SignUpRequest) {}

    @GetMapping
    fun test() = "bla"
}