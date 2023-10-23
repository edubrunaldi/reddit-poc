package com.reddit.backend.redditbackend.user.exception

class PasswordMissMatchException: RuntimeException(MESSAGE) {
    companion object {
        const val MESSAGE = "Wrong password."
    }
}