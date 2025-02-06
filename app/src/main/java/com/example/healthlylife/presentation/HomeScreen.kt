package com.example.healthlylife.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.healthlylife.R
import com.example.healthlylife.components.CustomButtonHome
import com.example.healthlylife.viewmodel.FoodViewModel

@SuppressLint("DefaultLocale")
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: FoodViewModel = hiltViewModel()
) {

    val caloriesEaten = viewModel.caloriesEaten.value
    val proteinsEaten = viewModel.proteinsEaten.value
    val carbsEaten = viewModel.carbsEaten.value
    val fatEaten = viewModel.fatEaten.value

    val proteinsEatenFormatted = String.format("%.1f", proteinsEaten)
    val carbsEatenFormatted = String.format("%.1f", carbsEaten)
    val fatEatenFormatted = String.format("%.1f", fatEaten)


        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.home_background),
                contentDescription = "Background",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

        Spacer(modifier = Modifier.height(60.dp))

        IconButton(
            onClick = { navController.navigate("profile") },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 20.dp, top = 50.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_person_outline_24),
                contentDescription = "Profile",
                tint = Color.White
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(50.dp))

            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "logo",
            )

            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 80.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "0",
                    fontSize = 28.sp,
                    color = Color.White,
                    modifier = Modifier.padding(start = 35.dp)
                )
                Text(
                    text = caloriesEaten.toInt().toString(),
                    fontSize = 28.sp,
                    color = Color.White,
                )
                Text(
                    text = "0",
                    fontSize = 28.sp,
                    color = Color.White,
                    modifier = Modifier.padding(end = 25.dp)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Eaten",
                    fontSize = 18.sp,
                    color = Color.White,
                    modifier = Modifier.padding(start = 40.dp)
                )
                Text(
                    text = "Goal",
                    fontSize = 22.sp,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Text(
                    text = "Burned",
                    fontSize = 18.sp,
                    color = Color.White,
                    modifier = Modifier.padding(end = 30.dp)
                )
            }

            Spacer(Modifier.height(85.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 20.dp)
                    .background(Color(0xFF292929), shape = RoundedCornerShape(12.dp))
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                MacroNutrientItem(label = "Proteins", value = proteinsEatenFormatted)
                MacroNutrientItem(label = "Carbs", value = carbsEatenFormatted)
                MacroNutrientItem(label = "Fat", value = fatEatenFormatted)
            }

            Spacer(Modifier.height(60.dp))

            CustomButtonHome(
                modifier = Modifier,
                text = "Add Meal",
                onClick = { navController.navigate("meals") },
                image = painterResource(id = R.drawable.meal),
            )

            Spacer(Modifier.height(20.dp))

            CustomButtonHome(
                modifier = Modifier.fillMaxWidth(),
                text = "Show Recipes",
                onClick = { navController.navigate("recipes") },
                image = painterResource(id = R.drawable.cookbook),
            )

            Spacer(Modifier.height(20.dp))

            CustomButtonHome(
                modifier = Modifier,
                text = "Show Recipes",
                onClick = { navController.navigate("calories") },
                image = painterResource(id = R.drawable.calories),

            )

        }
}
}

@Composable
fun MacroNutrientItem(label: String, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color.White
        )

        Text(
            text = value,
            fontSize = 18.sp,
            color = Color.White
        )
    }
}