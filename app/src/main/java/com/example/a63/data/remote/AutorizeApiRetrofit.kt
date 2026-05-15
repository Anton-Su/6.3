package com.example.a63.data.remote


import com.example.a63.data.model.UsersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AutorizeApiRetrofit {
    @GET("users")
    suspend fun getUsers(@Query("page") page: Int = 1, @Query("limit") limit: Int = 30): UsersResponse
}