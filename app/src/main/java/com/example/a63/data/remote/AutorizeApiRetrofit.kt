package com.example.a63.data.remote


import com.example.a63.data.model.LoginRequest
import com.example.a63.data.model.LoginResponse
import com.example.a63.data.model.UserDto
import com.example.a63.data.model.UsersResponse
import com.example.a63.domain.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface AutorizeApiRetrofit {

    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse

    @GET("users")
    suspend fun getUsers(
        @Query("limit") limit: Int = 30,
        @Query("skip") skip: Int = 0
    ): UsersResponse

    @GET("users/{id}")
    suspend fun getUserById(
        @Path("id") id: Int
    ): UserDto
}