package com.example.a63.domain.usecase

import com.example.a63.domain.model.User
import com.example.a63.domain.repository.UserRepository


class UserRepositoryUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(userId: Int): User {
        return repository.getUser(userId)
    }
}
