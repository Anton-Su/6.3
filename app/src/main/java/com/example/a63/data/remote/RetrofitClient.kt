package com.example.a63.data.remote

import android.content.Context
import com.example.a63.data.preferences.TokenManager
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import okhttp3.logging.HttpLoggingInterceptor


object RetrofitClient {
    private const val BASE_URL = "https://dummyjson.com/"
    val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    @Volatile
    private var initialized = false
    private lateinit var apiService: AutorizeApiRetrofit

    fun init(context: Context) {
        if (initialized) return

        synchronized(this) {
            if (initialized) return
            val tokenManager = TokenManager(context.applicationContext)
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(logging) // логирование
                .addInterceptor(AuthInterceptor(tokenManager))
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()
            apiService = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(okHttpClient)
                .build()
                .create(AutorizeApiRetrofit::class.java)
            initialized = true
        }
    }

    val api: AutorizeApiRetrofit
        get() {
            check(initialized) {
                "RetrofitClient is not initialized. Call RetrofitClient.init(context) first."
            }
            return apiService
        }
}