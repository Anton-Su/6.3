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
import com.example.a63.data.repository.AutorizeRepositoryImpl
import com.example.a63.domain.usecase.AutorizeRepositoryUseCase
import com.example.a63.navigation.Navigation
import com.example.a63.presentation.viewmodel.LoginViewModel
import com.example.a63.ui.theme._63Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val useCase = AutorizeRepositoryUseCase(AutorizeRepositoryImpl())
        val vm = LoginViewModel(useCase)
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