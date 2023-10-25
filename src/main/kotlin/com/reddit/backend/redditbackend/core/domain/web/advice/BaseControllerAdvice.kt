package com.reddit.backend.redditbackend.core.domain.web.advice

import com.reddit.backend.redditbackend.core.domain.web.response.CustomErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class BaseControllerAdvice: ResponseEntityExceptionHandler() {

    internal fun buildResponse(
        status: HttpStatus,
        message: String?,
        body: Any? = null
    ): ResponseEntity<CustomErrorResponse> =
        ResponseEntity
            .status(status)
            .body(CustomErrorResponse(message = message, body = body))
}