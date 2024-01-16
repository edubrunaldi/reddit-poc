package com.reddit.backend.redditbackend.user.internal.service

import com.reddit.backend.redditbackend.user.internal.config.properties.PasswordProperties
import org.bouncycastle.crypto.generators.BCrypt
import org.springframework.stereotype.Service

@Service
class PasswordService(private val passwordProperties: PasswordProperties) {
    companion object {
        // DO NOT USE THIS ON PRODUCTION
        // Hardcoded salt & cost
        private val salt = "1234567890123456".toByteArray()
        private const val COST = 10
    }
    fun hashPassword(password: String): String =
        BCrypt.generate(password.toByteArray(), salt, COST)
            .toString()

    fun validatePasswordCriteria(password: String): Boolean =
        password.matches(passwordProperties.regex.toRegex())

}