package com.reddit.backend.redditbackend.user.service

import org.bouncycastle.crypto.generators.BCrypt
import org.springframework.stereotype.Service

@Service
class PasswordService {
    companion object {
        // DO NOT USE THIS ON PRODUCTION
        // Hardcoded salt & cost
        private val salt = "12345678901234567890".toByteArray()
        private const val COST = 10
    }
    fun hashPassword(password: String): String {
        val hash = BCrypt.generate(password.toByteArray(), salt, COST)
        return hash.toString()
    }
}