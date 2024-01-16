package com.reddit.backend.redditbackend.user.internal.web.controller

import com.reddit.backend.redditbackend.core.domain.entity.UserEntity
import org.apache.commons.lang3.RandomStringUtils
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.ResultMatcher
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath

class UserControllerTest: AbstractIntegrationTest() {

    companion object {
        private const val USER_ID = 1L
        private const val URI_GET_BY_ID = "/"
    }

    @BeforeAll
    fun buildDb() {
        buildUserEntity(USER_ID)
            .let { userRepository.save(it) }
    }

    @Test
    fun `Should get user by id when received valid id`() {

        val user = userRepository.findByIdOrNull(USER_ID)

        performGet(MockMvcResultMatchers.status().isOk)
            .also { println(it) }
            .andExpect(jsonPath("\$.name").value(user?.username))
            .andReturn()
    }

    private fun performGet(
        httpStatusExpected: ResultMatcher,
        id: Long? = USER_ID
    ) = super.mockMvc.perform(
        MockMvcRequestBuilders.get("$URI_GET_BY_ID/$id")
            .contentType(MediaType.APPLICATION_JSON)
    )
        .andDo(MockMvcResultHandlers.print())
        .andExpect(httpStatusExpected)


    private fun buildUserEntity(
        id: Long,
        username: String = RandomStringUtils.random(10),
        password: String = RandomStringUtils.random(10),
        email: String = RandomStringUtils.random(10)
    ) = UserEntity(id, username, password, email)
}