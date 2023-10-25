package com.reddit.backend.redditbackend.user.internal.web.advice

import com.reddit.backend.redditbackend.core.domain.web.advice.BaseControllerAdvice
import com.reddit.backend.redditbackend.core.domain.web.response.CustomErrorResponse
import com.reddit.backend.redditbackend.user.internal.exception.EmailExistsException
import com.reddit.backend.redditbackend.user.internal.exception.PasswordMissMatchException
import com.reddit.backend.redditbackend.user.internal.exception.UserNotFoundException
import com.reddit.backend.redditbackend.user.internal.exception.UsernameExistsException
import com.reddit.backend.redditbackend.user.internal.web.controller.UserController
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice(assignableTypes = [UserController::class])
class UserControllerAdvice: BaseControllerAdvice() {

    @ExceptionHandler(EmailExistsException::class)
    fun handleEmailExistsException(ex: EmailExistsException): ResponseEntity<CustomErrorResponse> =
        buildResponse(HttpStatus.BAD_REQUEST, ex.message)

    @ExceptionHandler(PasswordMissMatchException::class)
    fun handlePasswordMissMatchException(ex: PasswordMissMatchException): ResponseEntity<CustomErrorResponse> =
        buildResponse(HttpStatus.BAD_REQUEST, ex.message)

    @ExceptionHandler(UsernameExistsException::class)
    fun handleUsernameExistsException(ex: UsernameExistsException): ResponseEntity<CustomErrorResponse> =
        buildResponse(HttpStatus.BAD_REQUEST, ex.message)

    @ExceptionHandler(UserNotFoundException::class)
    fun handleUserNotFoundException(ex: UserNotFoundException): ResponseEntity<CustomErrorResponse> =
        buildResponse(HttpStatus.NOT_FOUND, ex.message)
}