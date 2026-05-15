package com.example.a63.domain.usecase

import com.example.a63.domain.repository.LogoutRepository

class LogoutUseCase(private val repository: LogoutRepository) {
    suspend operator fun invoke() {
        return repository.logout()
    }
}

