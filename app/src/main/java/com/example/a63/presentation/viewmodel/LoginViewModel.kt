package com.example.a63.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a63.domain.model.User
import com.example.a63.domain.usecase.UsersRepositoryUseCase
import com.example.a63.domain.usecase.LoginUseCase
import com.example.a63.domain.usecase.LogoutUseCase
import com.example.a63.domain.usecase.UserRepositoryUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.io.IOException


sealed class UiState<out T> {
    object Idle : UiState<Nothing>()
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}

class LoginViewModel(
    private val usersRepositoryUseCase: UsersRepositoryUseCase,
    private val loginUseCase: LoginUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val userRepositoryUseCase: UserRepositoryUseCase
) : ViewModel() {
    private val _username = MutableStateFlow("emilys")
    val username: StateFlow<String> = _username.asStateFlow()
    private val _password = MutableStateFlow("emilyspass")
    val password: StateFlow<String> = _password.asStateFlow()
    private val _loginState = MutableStateFlow<UiState<Unit>>(UiState.Idle)
    val loginState: StateFlow<UiState<Unit>> = _loginState.asStateFlow()
    private val _isLoggedIn = MutableStateFlow(false)
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users = _users.asStateFlow()

    private val _user = MutableStateFlow(User(0, "", "", "", "", "", age = 0))
    val user = _user.asStateFlow()

    private val _navigation = MutableSharedFlow<String>()
    val navigation = _navigation.asSharedFlow()

    init {
        loadUsers()
    }

    private fun loadUsers() {
        viewModelScope.launch {

            _users.value = usersRepositoryUseCase()


//            delay(500)
//            _users.value = listOf(
//                User(1, "John", "Doe", "johndoe", "john@example.com", "https://i.pravatar.cc/150?img=1", age = 30),
//                User(2, "Jane", "Smith", "janesmith", "jane@example.com", "https://i.pravatar.cc/150?img=2", age = 25),
//                User(3, "Bob", "Johnson", "bobjohnson", "bob@example.com", "https://i.pravatar.cc/150?img=3", age = 28),
//                User(4, "Alice", "Williams", "alicew", "alice@example.com", "https://i.pravatar.cc/150?img=4", age = 32),
//                User(5, "Charlie", "Brown", "charlie", "charlie@example.com", "https://i.pravatar.cc/150?img=5", age = 22)
//            )
        }
    }

    fun loadUser(userId: Int) {
        viewModelScope.launch {
            try {
                val result = userRepositoryUseCase(userId)
                _user.value = result
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    fun updateUsername(value: String) {
        _username.value = value
    }
    fun updatePassword(value: String) {
        _password.value = value
    }

    fun onLogin() {
        viewModelScope.launch {
            _loginState.value = UiState.Loading
            try {
                delay(900)
                loginUseCase(_username.value, _password.value)
                _loginState.value = UiState.Success(Unit)
                _isLoggedIn.value = true
            } catch (e: IOException) {
                _loginState.value = UiState.Error("Нет соединения")
                _isLoggedIn.value = false
            } catch (e: Exception) {
                _loginState.value = UiState.Error(e.message ?: "Ошибка")
                _isLoggedIn.value = false
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            try {
                logoutUseCase()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _username.value = ""
                _password.value = ""
                _loginState.value = UiState.Idle
                _isLoggedIn.value = false
            }
        }
    }

    fun logoutAndNavigateToLogin() {
        viewModelScope.launch {
            try {
                logoutUseCase()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _username.value = ""
                _password.value = ""
                _loginState.value = UiState.Idle
                _isLoggedIn.value = false
                _navigation.emit("login")
            }
        }
    }
}

