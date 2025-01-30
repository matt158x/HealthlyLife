package com.example.healthlylife.form

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.healthlylife.viewmodel.UserFormViewModel


@Composable
fun MultiStepUserForm(navController: NavController, viewModel: UserFormViewModel = hiltViewModel()) {
    var currentStep by remember { mutableIntStateOf(1) }


    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0xFF181414))
    )
    {
        AnimatedContent(
            targetState = currentStep,
            transitionSpec = {
                if (targetState > initialState) {
                    (slideInHorizontally(animationSpec = tween(300)) { fullWidth -> fullWidth } + fadeIn()).togetherWith(
                        slideOutHorizontally(animationSpec = tween(300)) { fullWidth -> -fullWidth } + fadeOut())
                }
                else {
                    (slideInHorizontally(animationSpec = tween(300)) { fullWidth -> -fullWidth } + fadeIn()).togetherWith(
                        slideOutHorizontally(animationSpec = tween(300)) { fullWidth -> fullWidth } + fadeOut())
                }.using(
                    SizeTransform(clip = false)
                )
            },
            modifier = Modifier.fillMaxSize(), label = "MultiStepUserForm"
        ) { step ->

            when (step) {
                1 -> GoalStep(
                    navController = navController,
                    onNext = { currentStep = 2 }
                )

                2 -> GenderStep(
                    onNext = { currentStep = 3 },
                    onBack = { currentStep = 1 }
                )

                3 -> BirthDateStep(
                    onNext = { currentStep = 4 },
                    onBack = { currentStep = 2 }
                )

                4 -> InfoStep(
                    onNext = { currentStep = 5 },
                    onBack = { currentStep = 3 }
                )

                5 -> SummaryStep(
                    onSubmit = {
                        viewModel.completeForm { success ->
                            if (success) {
                                navController.navigate("home") {
                                    popUpTo("multistepform") { inclusive = true }
                                }
                            } else {
                                println("Data registration failed")
                            }
                        }
                    },
                    onBack = { currentStep = 4 }
                )
            }
        }
    }
}