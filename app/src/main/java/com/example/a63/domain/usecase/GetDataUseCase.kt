package com.example.a63.domain.usecase

import com.example.a63.domain.model.User
import com.example.a63.domain.repository.Repository

/**
 * GetDataUseCase - usecase для получения данных
 */
class GetDataUseCase(private val repository: Repository) {
    suspend operator fun invoke(): List<User> {
        return repository.getData()
    }
}

