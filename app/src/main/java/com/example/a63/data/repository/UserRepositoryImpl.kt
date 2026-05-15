package com.example.a63.data.repository

import com.example.a63.data.model.UserDto
import com.example.a63.data.model.toDomain
import com.example.a63.data.remote.RetrofitClient
import com.example.a63.domain.model.User
import com.example.a63.domain.repository.UserRepository


class UserRepositoryImpl : UserRepository {
    override suspend fun getUser(userId: Int): User {
        return try {
            val response: UserDto =
                RetrofitClient.api.getUserById(userId)
            response.toDomain()
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }
}