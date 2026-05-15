package com.example.a63.domain.usecase

import com.example.a63.domain.model.User
import com.example.a63.domain.repository.AutorizeRepository

class AutorizeRepositoryUseCase(private val repository: AutorizeRepository) {
    suspend operator fun invoke(): List<User> {
        return repository.getUsers()
    }
}

