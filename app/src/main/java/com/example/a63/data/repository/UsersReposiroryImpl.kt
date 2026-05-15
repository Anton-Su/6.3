package com.example.a63.data.repository

import com.example.a63.data.remote.RetrofitClient
import com.example.a63.domain.model.User
import com.example.a63.domain.repository.UsersRepository
import com.example.a63.data.model.toDomain


class UsersRepositoryImpl : UsersRepository {
    override suspend fun getUsers(): List<User> {
        return try {
            val body = RetrofitClient.api.getUsers()
            body.users.map { it.toDomain() }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}


