package com.reddit.backend.redditbackend.user.service

import com.reddit.backend.redditbackend.user.config.properties.PasswordProperties
import org.bouncycastle.crypto.generators.BCrypt
import org.springframework.stereotype.Service

@Service
class PasswordService(private val passwordProperties: PasswordProperties) {
    companion object {
        // DO NOT USE THIS ON PRODUCTION
        // Hardcoded salt & cost
        private val salt = "12345678901234567890".toByteArray()
        private const val COST = 10
    }
    fun hashPassword(password: String): String =
        BCrypt.generate(password.toByteArray(), salt, COST)
            .toString()

    fun validatePasswordCriteria(password: String): Boolean =
        password.matches(passwordProperties.regex.toRegex())

}