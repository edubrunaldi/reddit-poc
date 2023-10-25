package com.reddit.backend.redditbackend.user.internal.exception

class UsernameExistsException(username: String): RuntimeException(MESSAGE.format(username)) {
    companion object {
        const val MESSAGE = "Username %s already exists."
    }
}