package com.reddit.backend.redditbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["com.reddit.backend.redditbackend.user"])
class RedditBackendApplication

fun main(args: Array<String>) {
    runApplication<RedditBackendApplication>(*args)
}
