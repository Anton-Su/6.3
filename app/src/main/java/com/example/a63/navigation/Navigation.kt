package com.example.a63.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
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
fun Navigation(navController: NavHostController, modifier: Modifier = Modifier, vm: LoginViewModel = viewModel()) {
    NavHost(navController = navController, startDestination = "login") {
        composable(Screen.LoginScreen.route) {
            LoginScreen(modifier = modifier, navHostController = navController, viewModel = vm)
        }
        composable(Screen.UsersListScreen.route) {
            UsersListScreen(
                modifier = modifier,
                navHostController = navController,
                viewModel = vm
            )
        }
        composable(Screen. UserDetailScreen.route,
            arguments = listOf(navArgument("userId") { type = NavType.IntType }))
        { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId")
            val users = vm.users.collectAsState().value
            val user = userId?.let { id ->
                users.find { it.id == id }
            }
            if (user != null) {
                UserDetailScreen(
                    modifier = modifier,
                    navHostController = navController,
                    viewModel = vm,
                    user = user
                )
            }
        }
    }
}

