package com.reddit.backend.redditbackend.user.internal.service

import com.reddit.backend.redditbackend.core.domain.entity.UserEntity
import com.reddit.backend.redditbackend.core.domain.repository.UserRepository
import com.reddit.backend.redditbackend.user.internal.dto.CreateUserDto
import com.reddit.backend.redditbackend.user.internal.dto.LoginDto
import com.reddit.backend.redditbackend.user.internal.dto.toEntity
import com.reddit.backend.redditbackend.user.internal.exception.EmailExistsException
import com.reddit.backend.redditbackend.user.internal.exception.PasswordMissMatchException
import com.reddit.backend.redditbackend.user.internal.exception.UserNotFoundException
import com.reddit.backend.redditbackend.user.internal.exception.InvalidPasswordException
import com.reddit.backend.redditbackend.user.internal.exception.UsernameExistsException
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class UserServiceTest {

    companion object {
        private val createUserDto = CreateUserDto("eduardo", "123@abcABC", "eduardo@gmail.com")
        private val loginDto = LoginDto("eduardo", "123@abcABC")
    }

    @MockK
    private lateinit var passwordService: PasswordService

    @MockK
    private lateinit var userRepository: UserRepository

    @InjectMockKs
    private lateinit var userService: UserService

    @Test
    fun `Should create user when send all valid fields`() {

        every { userRepository.existsByUsername(createUserDto.username) } returns false
        every { userRepository.existsByEmail(createUserDto.email) } returns false
        every { passwordService.validatePasswordCriteria(createUserDto.password) } returns true
        every { passwordService.hashPassword(createUserDto.password) } returns "[B@1806862f"
        every { userRepository.save(any()) } returns createUserDto.toEntity().apply { id = 1L }

        userService.createUser(createUserDto)
            .also {
                assertThat(it.id).isNotNull()
                assertThat(it.username).isEqualTo(createUserDto.username)
                assertThat(it.email).isEqualTo(createUserDto.email)
            }
    }

    @Test
    fun `Should throw username exists exception when send a username that already exists`() {
        every { userRepository.existsByUsername(createUserDto.username) } returns true


        assertThrows<UsernameExistsException> {userService.createUser(createUserDto)}
            .also { assertThat(it.message).isEqualTo(UsernameExistsException.MESSAGE.format(createUserDto.username)) }
    }

    @Test
    fun `Should throw username exists exception when send a email that already exists`() {
        every { userRepository.existsByUsername(createUserDto.username) } returns false
        every { userRepository.existsByEmail(createUserDto.email) } returns true

        assertThrows<EmailExistsException> {userService.createUser(createUserDto)}
            .also { assertThat(it.message).isEqualTo(EmailExistsException.MESSAGE.format(createUserDto.username)) }
    }

    @Test
    fun `Should throw username exists exception when send an invalid password`() {
        every { userRepository.existsByUsername(createUserDto.username) } returns false
        every { userRepository.existsByEmail(createUserDto.email) } returns false
        every { passwordService.validatePasswordCriteria(createUserDto.password) } returns false

        assertThrows<InvalidPasswordException> {userService.createUser(createUserDto)}
            .also { assertThat(it.message).isEqualTo(InvalidPasswordException.MESSAGE) }
    }

    @Test
    fun `Should return success when send valid login`() {
        every { userRepository.findByUsername(loginDto.username) } returns UserEntity(1, loginDto.username, loginDto.password, "email@email.com")
        every { passwordService.hashPassword(loginDto.password) } returns loginDto.password

        userService.loginUser(loginDto)
            .also { assertThat(it.username).isEqualTo(loginDto.username) }
    }

    @Test
    fun `Should throw user not found when send a invalid username`() {
        every { userRepository.findByUsername(loginDto.username) } returns null

        assertThrows<UserNotFoundException> { userService.loginUser(loginDto) }
    }

    @Test
    fun `Should throw password miss match when send a invalid password`() {
        every { userRepository.findByUsername(loginDto.username) } returns UserEntity(1, loginDto.username, loginDto.password, "email@email.com")
        every { passwordService.hashPassword(loginDto.password) } returns "wrong password"

        assertThrows<PasswordMissMatchException> { userService.loginUser(loginDto) }
    }






}