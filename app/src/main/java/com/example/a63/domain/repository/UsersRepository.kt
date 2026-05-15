package com.example.a63.domain.repository

import com.example.a63.domain.model.User

interface UsersRepository {
    suspend fun getUsers(): List<User>
}


