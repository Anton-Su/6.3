package com.example.a63.data.model

import com.example.a63.domain.model.User

data class UserDto(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val username: String,
    val email: String,
    val image: String?,
    val age: Int?,
)

fun UserDto.toDomain(): User {
    return User(
        id = id,
        firstName = firstName,
        lastName = lastName,
        username = username,
        email = email,
        image = image,
        age = age
    )
}


data class UsersResponse(
    val users: List<UserDto>,
    val total: Int,
    val skip: Int,
    val limit: Int
)


data class LoginRequest(
    val username: String,
    val password: String
)

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
    val id: Int,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val image: String?
)