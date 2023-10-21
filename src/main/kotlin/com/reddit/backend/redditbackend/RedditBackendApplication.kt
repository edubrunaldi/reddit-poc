package com.reddit.backend.redditbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RedditBackendApplication

fun main(args: Array<String>) {
    runApplication<RedditBackendApplication>(*args)
}
