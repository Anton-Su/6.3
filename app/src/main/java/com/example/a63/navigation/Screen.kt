package com.example.a63.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.a63.presentation.ui.screen.LoginScreen
import com.example.a63.presentation.ui.screen.UserDetailScreen
import com.example.a63.presentation.ui.screen.UsersListScreen
import com.example.a63.presentation.viewmodel.LoginViewModel



sealed class Screen(val route: String) {
    data object LoginScreen : Screen("login")
    data object UsersListScreen : Screen("users")
    data object UserDetailScreen : Screen("user_detail/{userId}") {
        fun createRoute(userId: Int) = "user_detail/$userId"
    }
}


@Composable
fun Navigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            val vm: LoginViewModel = viewModel()
            LoginScreen(modifier = modifier, navHostController = navController, viewModel = vm)
        }

        composable("users") {
            UsersListScreen(navHostController = navController)
        }

        composable("user_detail") {
            UserDetailScreen(navHostController = navController)
        }
    }
}

