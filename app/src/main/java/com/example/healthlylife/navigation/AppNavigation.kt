package com.example.healthlylife.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.healthlylife.auth.AuthState
import com.example.healthlylife.presentation.CaloriesScreen
import com.example.healthlylife.presentation.HomeScreen
import com.example.healthlylife.presentation.LoginScreen
import com.example.healthlylife.presentation.MealsAddScreen
import com.example.healthlylife.presentation.MealsScreen
import com.example.healthlylife.presentation.MultiStepUserForm
import com.example.healthlylife.presentation.ProfileScreen
import com.example.healthlylife.presentation.RecipeDetailScreen
import com.example.healthlylife.presentation.RecipesScreen
import com.example.healthlylife.presentation.RegisterScreen
import com.example.healthlylife.presentation.StartScreen
import com.example.healthlylife.viewmodel.SharedViewModel

@Composable
fun AppNavigation(sharedViewModel: SharedViewModel = viewModel()) {
    val navController = rememberNavController()
    val authState by sharedViewModel.authState.observeAsState(AuthState.Loading)

    NavHost(
        navController = navController,
        startDestination = "start",
        enterTransition = { fadeIn(animationSpec = tween(300)) },
        exitTransition = { fadeOut(animationSpec = tween(300)) }
    ) {
        composable("start") {
            StartScreen(navController, sharedViewModel)
        }
        composable("login") {
            LoginScreen(navController, sharedViewModel)
        }
        composable("signup") {
            RegisterScreen(navController, sharedViewModel)
        }
        composable("profile") {
            ProfileScreen(navController, sharedViewModel)
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
        composable("recipes_detail/{imageId}") {
                backStackEntry ->
            val imageId = backStackEntry.arguments?.getString("imageId")?.toIntOrNull()
            if (imageId != null) {
                RecipeDetailScreen(navController, imageId)
            }
        }
        composable("meals") {
            MealsScreen(navController)
        }
        composable("meals_add") {
            MealsAddScreen(navController)
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