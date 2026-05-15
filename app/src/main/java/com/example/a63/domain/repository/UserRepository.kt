package com.example.a63.domain.repository

import com.example.a63.domain.model.User

interface UserRepository {
    suspend fun getUser(userId: Int): User
}