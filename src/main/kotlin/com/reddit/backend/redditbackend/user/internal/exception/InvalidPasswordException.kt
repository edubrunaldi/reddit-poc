package com.reddit.backend.redditbackend.user.internal.exception

class InvalidPasswordException: RuntimeException(MESSAGE) {
    companion object {
        const val MESSAGE = "Invalid Password."
    }
}