package com.reddit.backend.redditbackend.core.domain.web.advice

import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.springframework.http.HttpStatus

@ExtendWith(MockKExtension::class)
class BaseControllerAdviceTest {

    companion object {
        private const val MESSAGE = "MESSAGE"
        private const val BODY = "BODY"
    }

    @InjectMocks private var baseControllerAdvice = BaseControllerAdvice()

    @Test
    fun `Should return a response entity with status 400, no message and no body`() {
        baseControllerAdvice.buildResponse(HttpStatus.BAD_REQUEST, null, null)
            .also { assertThat(it.statusCode.is4xxClientError).isTrue() }
            .also { assertThat(it.body?.message).isNull() }
            .also { assertThat(it.body?.body).isNull() }
    }

    @Test
    fun `Should return a response entity with status 400, with message and no body`() {
        baseControllerAdvice.buildResponse(HttpStatus.BAD_REQUEST, MESSAGE, null)
            .also { assertThat(it.statusCode.is4xxClientError).isTrue() }
            .also { assertThat(it.body?.message).isEqualTo(MESSAGE) }
            .also { assertThat(it.body?.body).isNull() }
    }

    @Test
    fun `Should return a response entity with status 400, with message and body`() {
        baseControllerAdvice.buildResponse(HttpStatus.BAD_REQUEST, MESSAGE, BODY)
            .also { assertThat(it.statusCode.is4xxClientError).isTrue() }
            .also { assertThat(it.body?.message).isEqualTo(MESSAGE) }
            .also { assertThat(it.body?.body).isEqualTo(BODY) }
    }
}