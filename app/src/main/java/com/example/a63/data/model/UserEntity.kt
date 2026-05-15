package com.example.a63.data.model

import com.example.a63.domain.model.User

data class UserEntity(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val username: String,
    val email: String,
    val avatar: String?
)

fun UserEntity.toDomain(): User {
    return User(
        id = id,
        firstName = firstName,
        lastName = lastName,
        username = username,
        email = email,
        avatar = avatar
    )
}