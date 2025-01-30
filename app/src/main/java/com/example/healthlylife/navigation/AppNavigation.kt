package com.example.healthlylife.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.healthlylife.form.MultiStepUserForm
import com.example.healthlylife.presentation.CaloriesScreen
import com.example.healthlylife.presentation.HomeScreen
import com.example.healthlylife.presentation.LoginScreen
import com.example.healthlylife.presentation.MealsScreen
import com.example.healthlylife.presentation.ProfileScreen
import com.example.healthlylife.presentation.RecipesScreen
import com.example.healthlylife.presentation.RegisterScreen
import com.example.healthlylife.presentation.StartScreen

@Composable
fun AppNavigation(authViewModel: AuthViewModel) {
    val navController = rememberNavController()
    val authState by authViewModel.authState.observeAsState(AuthState.Loading)

    NavHost(
        navController = navController,
        startDestination = "start",
        enterTransition = { fadeIn(animationSpec = tween(300)) },
                exitTransition = { fadeOut(animationSpec = tween(300)) }
    ) {
        composable("start") {
            StartScreen(navController, authViewModel)
        }
        composable("login") {
            LoginScreen(navController, authViewModel)
        }
        composable("signup") {
            RegisterScreen(navController, authViewModel)
        }
        composable("profile") {
            ProfileScreen(navController, authViewModel)
        }
        composable("form") {
            MultiStepUserForm(navController)
        }
        composable("home") {
            HomeScreen(navController)
        }
        composable("calories"){
            CaloriesScreen(navController)
        }
        composable("recipes") {
            RecipesScreen(navController)
        }
        composable("meals") {
            MealsScreen(navController)
        }


    }

    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Authenticated -> navController.navigate("home") {
                popUpTo("start") { inclusive = true }
            }

            is AuthState.FormNotCompleted -> navController.navigate("form") {
                popUpTo("start") { inclusive = true }
            }

            is AuthState.Unauthenticated -> navController.navigate("start") {
                popUpTo("start") { inclusive = true }
            }
            else -> Unit
        }
    }
}