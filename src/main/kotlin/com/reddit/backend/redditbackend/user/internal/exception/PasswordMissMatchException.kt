package com.reddit.backend.redditbackend.user.internal.exception

class PasswordMissMatchException: RuntimeException(MESSAGE) {
    companion object {
        const val MESSAGE = "Wrong password."
    }
}