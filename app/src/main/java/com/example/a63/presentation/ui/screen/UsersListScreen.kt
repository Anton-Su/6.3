package com.example.a63.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.a63.navigation.Screen
import com.example.a63.presentation.ui.component.UserCard
import com.example.a63.presentation.viewmodel.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersListScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    viewModel: LoginViewModel = viewModel()
) {
    val users = viewModel.users.collectAsState().value
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Пользователи") },
                actions = {
                    TextButton(
                        onClick = {
                            viewModel.logout()
                            navHostController.navigate(Screen.LoginScreen.route) {
                                popUpTo(Screen.UsersListScreen.route) { inclusive = true }
                                launchSingleTop = true
                            }
                        }
                    ) {
                        Text("Выйти")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(users) { user ->
                UserCard(
                    user = user,
                    onClick = {
                        navHostController.navigate(Screen.UserDetailScreen.createRoute(user.id))
                    }
                )
            }
        }
    }
}