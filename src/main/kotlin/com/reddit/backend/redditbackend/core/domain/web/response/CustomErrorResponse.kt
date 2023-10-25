package com.reddit.backend.redditbackend.core.domain.web.response

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class CustomErrorResponse(
    val message: String?,
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val body: Any? = null
)