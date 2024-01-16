package com.reddit.backend.redditbackend.user.internal.service

import com.reddit.backend.redditbackend.user.internal.config.properties.PasswordProperties
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class PasswordServiceTest {
    companion object {
        private const val PASSWORD = "12341234"
        private const val VALID_PASSWORD = "123@aAbc"
        private const val PASSWORD_NO_NUMBER = "abcABC@!#"
        private const val PASSWORD_NO_SPECIAL_CHARACTER = "abc12345"
        private const val PASSWORD_NO_LOWERCASE_CHARACTER = "ABC@1234"
        private const val PASSWORD_NO_UPPERCASE_CHARACTER = "abc@1234"
        private const val REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+|~-]).{8,}$"
    }

    @MockK
    private lateinit var passwordProperties: PasswordProperties

    @InjectMockKs
    private lateinit var passwordService: PasswordService

    @Test
    fun `Should return a hash with 11 characters when send 12341234 password`() {
        passwordService.hashPassword(PASSWORD)
            .also { assertThat(it.length).isEqualTo(11) }
    }

    @Test
    fun `Should return true when validate the password criteria`() {
        every { passwordProperties.regex } returns REGEX

        passwordService.validatePasswordCriteria(VALID_PASSWORD)
            .also { assertThat(it).isTrue() }
    }

    @Test
    fun `Should return false when validate the password without number`() {
        every { passwordProperties.regex } returns REGEX

        passwordService.validatePasswordCriteria(PASSWORD_NO_NUMBER)
            .also { assertThat(it).isFalse() }
    }

    @Test
    fun `Should return false when validate the password without special character`() {
        every { passwordProperties.regex } returns REGEX

        passwordService.validatePasswordCriteria(PASSWORD_NO_SPECIAL_CHARACTER)
            .also { assertThat(it).isFalse() }
    }

    @Test
    fun `Should return false when validate the password without lowercase character`() {
        every { passwordProperties.regex } returns REGEX

        passwordService.validatePasswordCriteria(PASSWORD_NO_LOWERCASE_CHARACTER)
            .also { assertThat(it).isFalse() }
    }

    @Test
    fun `Should return false when validate the password without uppercase character`() {
        every { passwordProperties.regex } returns REGEX

        passwordService.validatePasswordCriteria(PASSWORD_NO_UPPERCASE_CHARACTER)
            .also { assertThat(it).isFalse() }
    }
}