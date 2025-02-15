package com.example.healthlylife.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.healthlylife.components.CustomButton
import com.example.healthlylife.components.MacroInputField
import com.example.healthlylife.viewmodel.MealsAddViewModel

@Composable
fun MealsAddScreen(
    navController: NavController,
    viewModel: MealsAddViewModel = hiltViewModel()
) {
    var mealName by remember { mutableStateOf("") }
    var calories by remember { mutableStateOf("") }
    var protein by remember { mutableStateOf("") }
    var carbs by remember { mutableStateOf("") }
    var fat by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF181414))
    ) {
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

        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = "Meal name",
                fontFamily = damionFontFamily,
                color = Color.White,
                fontSize = 32.sp
            )

            Spacer(Modifier.height(20.dp))

            TextField(
                value = mealName,
                onValueChange = { mealName = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                placeholder = { Text(text = "Name", color = Color.Gray) },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFF292929),
                    focusedContainerColor = Color(0xFF292929),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Macro 100g",
                fontFamily = damionFontFamily,
                color = Color.White,
                fontSize = 32.sp
            )

            MacroInputField(label = "Calories", value = calories) { calories = it }
            MacroInputField(label = "Protein", value = protein) { protein = it }
            MacroInputField(label = "Carbs", value = carbs) { carbs = it }
            MacroInputField(label = "Fat", value = fat) { fat = it }


            Spacer(modifier = Modifier.height(100.dp))

            CustomButton(
                onClick = {
                    viewModel.mealName = mealName
                    viewModel.calories = calories
                    viewModel.protein = protein
                    viewModel.carbs = carbs
                    viewModel.fat = fat

                    viewModel.addMeal(
                        onSuccess = { navController.navigate("meals") },
                        onError = { }
                    )
                },
                text = "Add Meal",
                textColor = Color.Black,
                textSize = 18.sp,
            )
        }
    }
}

