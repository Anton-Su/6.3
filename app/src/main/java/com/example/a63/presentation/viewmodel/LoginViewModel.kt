package com.example.a63.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException


sealed class UiState<out T> {
    object Idle : UiState<Nothing>()
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}


class LoginViewModel : ViewModel() {
    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _loginState = MutableStateFlow<UiState<Unit>>(UiState.Idle)
    val loginState: StateFlow<UiState<Unit>> = _loginState.asStateFlow()

    fun updateUsername(value: String) {
        _username.value = value
    }

    fun updatePassword(value: String) {
        _password.value = value
    }

    fun resetState() {
        _loginState.value = UiState.Idle
    }

    fun onLogin() {
        // Простая имитация логина — позже замените на вызов репозитория/retrofit
        viewModelScope.launch {
            _loginState.value = UiState.Loading
            try {
                // имитируем сетевой запрос
                delay(1200)

                // Симуляция ошибок: если username == "no_internet" -> IOException
                if (_username.value == "no_internet") throw IOException("No connection")

                if (_username.value == "user" && _password.value == "password") {
                    _loginState.value = UiState.Success(Unit)
                } else {
                    _loginState.value = UiState.Error("Неверные данные")
                }
            } catch (e: IOException) {
                _loginState.value = UiState.Error("Нет соединения")
            } catch (e: Exception) {
                _loginState.value = UiState.Error(e.message ?: "Ошибка")
            }
        }
    }
}

