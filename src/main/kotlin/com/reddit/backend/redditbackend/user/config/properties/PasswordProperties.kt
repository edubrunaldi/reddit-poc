package com.reddit.backend.redditbackend.user.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("user.password")
data class PasswordProperties(val regex: String)