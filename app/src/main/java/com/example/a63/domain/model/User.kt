package com.example.a63.domain.model

/**
 * User - бизнес-модель для слоя Domain
 */
data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val username: String,
    val email: String,
    val image: String?,
    val age: Int?,
)

