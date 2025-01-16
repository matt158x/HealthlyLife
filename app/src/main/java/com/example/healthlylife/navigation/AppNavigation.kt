package com.example.healthlylife.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.healthlylife.form.MultiStepUserForm
import com.example.healthlylife.presentation.HomeScreen
import com.example.healthlylife.presentation.LoginScreen
import com.example.healthlylife.presentation.RegisterScreen
import com.example.healthlylife.presentation.StartScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier, authViewModel: AuthViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "start", builder = {
        composable("start") {
            StartScreen(navController)
        }
        composable ("login") {
            LoginScreen(modifier,navController,authViewModel)
        }
        composable("signup"){
            RegisterScreen(modifier,navController,authViewModel)
        }
        composable("home"){
            HomeScreen(modifier,navController,authViewModel)
        }
        composable("form") {
            MultiStepUserForm(navController)
        }
    })
}
