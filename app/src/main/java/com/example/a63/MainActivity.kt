package com.example.a63

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.a63.data.preferences.TokenManager
import com.example.a63.data.remote.RetrofitClient
import com.example.a63.data.repository.LoginRepositoryImpl
import com.example.a63.data.repository.LogoutRepositoryImpl
import com.example.a63.data.repository.UserRepositoryImpl
import com.example.a63.data.repository.UsersRepositoryImpl
import com.example.a63.domain.usecase.UsersRepositoryUseCase
import com.example.a63.domain.usecase.LoginUseCase
import com.example.a63.domain.usecase.LogoutUseCase
import com.example.a63.domain.usecase.UserRepositoryUseCase
import com.example.a63.navigation.Navigation
import com.example.a63.presentation.viewmodel.LoginViewModel
import com.example.a63.ui.theme._63Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        RetrofitClient.init(applicationContext)
        val tokenManager = TokenManager(applicationContext)
        val usersUseCase = UsersRepositoryUseCase(UsersRepositoryImpl())
        val userUseCase = UserRepositoryUseCase(UserRepositoryImpl())
        val loginUseCase = LoginUseCase(LoginRepositoryImpl(tokenManager))
        val logoutUseCase = LogoutUseCase(LogoutRepositoryImpl(tokenManager))
        val vm = LoginViewModel(usersUseCase, loginUseCase, logoutUseCase, userUseCase)
        setContent {
            _63Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Navigation(
                        navController = rememberNavController(),
                        modifier = Modifier.padding(innerPadding),
                        vm = vm
                    )
                }
            }
        }
    }
}