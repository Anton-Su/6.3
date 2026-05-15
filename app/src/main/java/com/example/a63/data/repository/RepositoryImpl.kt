package com.example.a63.data.repository

import com.example.a63.domain.model.User
import com.example.a63.domain.repository.Repository

/**
 * RepositoryImpl - реализация репозитория для работы с данными
 */
class RepositoryImpl: Repository {
    override suspend fun getData(): List<User> {
        TODO("Not yet implemented")
    }
}

