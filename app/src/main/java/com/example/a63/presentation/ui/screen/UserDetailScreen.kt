package com.example.a63.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.a63.domain.model.User
import com.example.a63.navigation.Screen
import com.example.a63.presentation.viewmodel.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    user: User,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TopAppBar(
            title = {
                Text("Детали пользователя")
            },
            navigationIcon = {
                IconButton(
                    onClick = { navHostController.popBackStack() }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Назад"
                    )
                }
            }
        )
        AsyncImage(
            model = user.image,
            contentDescription = "${user.firstName} ${user.lastName}",
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Text(
            text = "${user.firstName} ${user.lastName}",
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            modifier = Modifier.padding(top = 24.dp)
        )
        Text(
            text = user.email,
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 12.dp),
            color = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = "@${user.username}",
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 8.dp),
            color = androidx.compose.material3.MaterialTheme.colorScheme.outline
        )
        Text(
            text = "${user.age}",
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 8.dp),
            color = androidx.compose.material3.MaterialTheme.colorScheme.outline
        )
    }
}
