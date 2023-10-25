package com.reddit.backend.redditbackend.user.internal.exception

class UserNotFoundException: RuntimeException(MESSAGE) {
    companion object {
        const val MESSAGE = "User not found."
    }
}