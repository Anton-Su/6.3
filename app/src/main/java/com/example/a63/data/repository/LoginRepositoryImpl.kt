package com.example.a63.data.repository

import com.example.a63.data.model.LoginRequest
import com.example.a63.data.preferences.TokenManager
import com.example.a63.data.remote.RetrofitClient
import com.example.a63.domain.repository.LoginRepository

class LoginRepositoryImpl(private val tokenManager: TokenManager) : LoginRepository {
    override suspend fun login(username: String, password: String) {

        val response = RetrofitClient.api.login(
            LoginRequest(username = username, password = password)
        )

        tokenManager.saveAccessToken(response.accessToken)
        tokenManager.saveRefreshToken(response.refreshToken)
    }
}

