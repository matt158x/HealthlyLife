package com.example.healthlylife.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.healthlylife.R
import com.example.healthlylife.components.CustomButtonHome
import com.example.healthlylife.components.MacroNutrientItem
import com.example.healthlylife.viewmodel.HomeScreenViewModel

@SuppressLint("DefaultLocale")
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {

    val caloriesEaten = viewModel.caloriesEaten.value
    val proteinsEaten = viewModel.proteinsEaten.value
    val carbsEaten = viewModel.carbsEaten.value
    val fatEaten = viewModel.fatEaten.value
    val bmr = viewModel.bmr.value

    val remainingCalories = maxOf(bmr - caloriesEaten.toInt(),0)

    val proteinsEatenFormatted = String.format("%.1f", proteinsEaten)
    val carbsEatenFormatted = String.format("%.1f", carbsEaten)
    val fatEatenFormatted = String.format("%.1f", fatEaten)

    val progress = if (bmr > 0) {
        1 - (remainingCalories.toFloat() / bmr.toFloat())
    } else {
        0f
    }

        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.home_background),
                contentDescription = "Background",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

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
                Spacer(modifier = Modifier.height(40.dp))

                Image(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = "logo",
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy((-10).dp)) {
                        Text(
                            text = caloriesEaten.toInt().toString(),
                            fontSize = 22.sp,
                            color = Color.White,
                            fontFamily = alkatrFontFamily
                        )
                        Text(
                            text = "EATEN",
                            fontSize = 12.sp,
                            color = Color.White,
                            fontFamily = alkatrFontFamily
                        )
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.size(150.dp)
                        ) {
                            CircularProgressIndicator(
                                progress = progress,
                                modifier = Modifier.fillMaxSize(),
                                color = Color.Green,
                                strokeWidth = 20f
                            )

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy((-10).dp)) {
                                Text(
                                    text = remainingCalories.toString(),
                                    fontSize = 32.sp,
                                    color = Color.White,
                                    fontFamily = alkatrFontFamily
                                )

                                Text(
                                    text = "KCAL LEFT",
                                    fontSize = 12.sp,
                                    color = Color.White,
                                    fontFamily = alkatrFontFamily
                                )
                            }

                        }

                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy((-10).dp)) {
                        Text(
                            text = "0",
                            fontSize = 22.sp,
                            color = Color.White,
                            fontFamily = alkatrFontFamily
                        )
                        Text(
                            text = "BURNED",
                            fontSize = 12.sp,
                            color = Color.White,
                            fontFamily = alkatrFontFamily,
                        )
                    }
                }




            Spacer(Modifier.height(55.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(horizontal = 20.dp)
                    .background(Color(0xFF292929), shape = RoundedCornerShape(12.dp)),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                MacroNutrientItem(label = "Proteins", value = proteinsEatenFormatted, modifier = Modifier.padding(top = 5.dp))
                MacroNutrientItem(label = "Carbs", value = carbsEatenFormatted, modifier = Modifier.padding(top = 5.dp))
                MacroNutrientItem(label = "Fat", value = fatEatenFormatted, modifier = Modifier.padding(top = 5.dp))
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
                text = "Show Training plans",
                onClick = { navController.navigate("calories") },
                image = painterResource(id = R.drawable.calories),

            )

        }
}
}


@Composable
fun CircularProgressIndicator(
    progress: Float, // Procent postępu (0.0 do 1.0)
    modifier: Modifier = Modifier,
    color: Color = Color.Green, // Kolor wypełnienia
    strokeWidth: Float = 20f // Grubość linii
) {
    Canvas(modifier = modifier) {
        // Rysowanie tła okręgu
        drawArc(
            color = Color.LightGray,
            startAngle = -90f, // Zaczynamy od góry (12:00)
            sweepAngle = 360f, // Pełny okrąg
            useCenter = false,
            style = Stroke(strokeWidth, cap = StrokeCap.Round),
            size = Size(size.width, size.height)
        )

        // Rysowanie wypełnienia zgodnie z postępem
        drawArc(
            color = color,
            startAngle = -90f,
            sweepAngle = 360f * progress, // Wypełnienie zgodnie z postępem
            useCenter = false,
            style = Stroke(strokeWidth, cap = StrokeCap.Round),
            size = Size(size.width, size.height)
        )
    }
}


