package com.example.a63.domain.repository

interface LoginRepository {
    suspend fun login(username: String, password: String)
}

