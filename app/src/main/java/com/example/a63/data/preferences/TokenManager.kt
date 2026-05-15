package com.example.a63.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

val Context.tokenDataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_tokens")

class TokenManager(private val context: Context) {

    private val ACCESS_TOKEN = stringPreferencesKey("access_token")
    private val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
    private var cachedAccessToken: String? = null
    private var cachedRefreshToken: String? = null

    suspend fun loadTokens() {
        val prefs = context.tokenDataStore.data.first()
        cachedAccessToken = prefs[ACCESS_TOKEN]
        cachedRefreshToken = prefs[REFRESH_TOKEN]
    }

    fun getAccessToken(): String? = cachedAccessToken
    fun getRefreshToken(): String? = cachedRefreshToken

    suspend fun saveAccessToken(token: String) {
        cachedAccessToken = token
        context.tokenDataStore.edit { it[ACCESS_TOKEN] = token }
    }

    suspend fun saveRefreshToken(token: String) {
        cachedRefreshToken = token
        context.tokenDataStore.edit { it[REFRESH_TOKEN] = token }
    }

    suspend fun clearTokens() {
        cachedAccessToken = null
        cachedRefreshToken = null
        context.tokenDataStore.edit { it.clear() }
    }
}