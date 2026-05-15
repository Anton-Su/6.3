package com.example.a63.domain.usecase

import com.example.a63.domain.model.User
import com.example.a63.domain.repository.UsersRepository

class UsersRepositoryUseCase(private val repository: UsersRepository) {
    suspend operator fun invoke(): List<User> {
        return repository.getUsers()
    }
}



