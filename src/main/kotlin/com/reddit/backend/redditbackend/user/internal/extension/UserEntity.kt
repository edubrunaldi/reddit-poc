package com.reddit.backend.redditbackend.user.internal.extension

import com.reddit.backend.redditbackend.core.domain.entity.UserEntity
import com.reddit.backend.redditbackend.user.internal.web.response.UserResponse

fun UserEntity.toUserResponse(): UserResponse = UserResponse(id, username, email)