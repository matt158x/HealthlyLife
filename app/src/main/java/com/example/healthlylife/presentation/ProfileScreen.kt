package com.example.healthlylife.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.healthlylife.components.CustomButton
import com.example.healthlylife.viewmodel.AuthViewModel

@Composable
fun ProfileScreen(navController: NavController, authViewModel: AuthViewModel) {


    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0xFF181414))
    )
    {

            Spacer(modifier=Modifier.height(60.dp))

            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 20.dp, top = 50.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }

            CustomButton(
                text = "Log out",
                modifier = Modifier.align(Alignment.Center),
                onClick = {
                authViewModel.signout()
            },
                textColor = Color.Black
            )

            }

    }

