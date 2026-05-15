package com.example.a63.domain.usecase

import com.example.a63.domain.repository.LoginRepository

class LoginUseCase(private val repository: LoginRepository) {
    suspend operator fun invoke(username: String, password: String) {
        return repository.login(username, password)
    }
}

