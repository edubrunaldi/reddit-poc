package com.reddit.backend.redditbackend.user.exception

class EmailExistsException(email: String): RuntimeException(MESSAGE.format(email)) {
    companion object {
        const val MESSAGE = "Email %s already exists."
    }
}