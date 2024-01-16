package com.reddit.backend.redditbackend.user.internal.web.controller

import com.reddit.backend.redditbackend.RedditBackendApplication
import com.reddit.backend.redditbackend.core.domain.repository.UserRepository
import com.reddit.backend.redditbackend.user.internal.config.properties.PasswordProperties
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(classes = [RedditBackendApplication::class], webEnvironment = RANDOM_PORT)
@EnableConfigurationProperties(PasswordProperties::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class AbstractIntegrationTest {

    @Autowired
    internal lateinit var mockMvc: MockMvc

    @Autowired
    internal lateinit var userRepository: UserRepository
}