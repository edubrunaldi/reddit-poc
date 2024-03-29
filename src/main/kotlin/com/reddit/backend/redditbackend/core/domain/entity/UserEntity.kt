package com.reddit.backend.redditbackend.core.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime


@Entity
@Table(
    name = "USER",
    indexes =  [
        Index(name="IDX_USERNAME", columnList = "DES_USERNAME")
    ]
)
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDT_USER")
    var id: Long? = null,

    @Column(name = "DES_USERNAME", unique = true, nullable = false, updatable = false)
    val username: String,

    @Column(name = "DES_PASSWORD", nullable = false, updatable = true)
    val password: String,

    @Column(name = "DES_EMAIL", nullable = false, updatable = false)
    val email: String,

    @Column(name = "DAT_CREATED_AT")
    @CreatedDate
    var createdAt: LocalDateTime? = null,

    @Column(name = "DAT_UPDATED_AT")
    @LastModifiedDate
    var updatedAt: LocalDateTime? = null,
)
