package com.example.a63.data.repository

import com.example.a63.data.preferences.TokenManager
import com.example.a63.domain.repository.LogoutRepository

class LogoutRepositoryImpl(private val tokenManager: TokenManager) : LogoutRepository {
    override suspend fun logout() {
        tokenManager.clearTokens()
    }
}

