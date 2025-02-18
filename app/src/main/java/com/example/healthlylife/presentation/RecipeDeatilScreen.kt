package com.example.healthlylife.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.healthlylife.components.CustomButton
import com.example.healthlylife.components.MacroNutrientItem
import com.example.healthlylife.viewmodel.RecipeDetailScreenViewModel

@Composable
fun RecipeDetailScreen(
    navController: NavController,
    viewModel: RecipeDetailScreenViewModel = hiltViewModel(),
    imageId: Int
) {
    LaunchedEffect(imageId) {
        viewModel.loadRecipe(imageId)
    }

    val recipe by viewModel.selectedRecipe

    if (recipe != null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF181414))
        ) {
            Image(
                painter = painterResource(id = recipe!!.imageList.first()),
                contentDescription = recipe!!.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .align(Alignment.TopCenter),
                contentScale = ContentScale.Crop
            )


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


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .padding(top = 250.dp)
                    .background(Color(0xFF292929))
                    .padding(10.dp)
            ) {
                Text(
                    text = recipe!!.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 320.dp)
            ) {
                item {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            text = "Nutritional Information /100g",
                            fontSize = 12.sp,
                            color = Color.Gray,
                            fontFamily = alkatrFontFamily,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(top = 5.dp)
                            .padding(horizontal = 20.dp)
                            .background(Color(0xFF292929), shape = RoundedCornerShape(12.dp)),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        MacroNutrientItem(label = "Calories", value = recipe!!.calories.toString(), modifier = Modifier.padding(top = 5.dp))
                        MacroNutrientItem(label = "Proteins", value = recipe!!.proteins.toString(), modifier = Modifier.padding(top = 5.dp))
                        MacroNutrientItem(label = "Carbs", value = recipe!!.carbs.toString(), modifier = Modifier.padding(top = 5.dp))
                        MacroNutrientItem(label = "Fat", value = recipe!!.fat.toString(), modifier = Modifier.padding(top = 5.dp))
                    }
                }

                item {
                    Spacer(Modifier.height(20.dp))
                }


                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                            .height(30.dp)
                            .background(Color(0xFF292929),
                                shape = RoundedCornerShape(12.dp)
                            )

                    ) {
                        Text(
                            text = "Ingredients:",
                            fontSize = 18.sp,
                            color = Color.White,
                            fontFamily = alkatrFontFamily,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    Spacer(Modifier.height(20.dp))
                }


                item {

                    recipe!!.ingredients.forEach {
                        Text(
                            modifier = Modifier
                                .padding(start = 20.dp),
                            text = it,
                            fontFamily = alkatrFontFamily,
                            fontSize = 14.sp,
                            color = Color.White
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(20.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                            .height(30.dp)
                            .background(Color(0xFF292929),
                                shape = RoundedCornerShape(12.dp)
                            )
                    ) {

                        Text(
                            text = "Recipe:",
                            fontSize = 18.sp,
                            color = Color.White,
                            fontFamily = alkatrFontFamily,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        modifier = Modifier.padding(start = 20.dp),
                        text = recipe!!.instructions,
                        fontSize = 14.sp,
                        fontFamily = alkatrFontFamily,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    CustomButton(
                        text = "ADD TO FAVOURITES",
                        textColor = Color.Black,
                        onClick = { viewModel.addToFavorites(recipe!!)
                        navController.navigate("home")}
                    )
                }


            }
        }
    }
}

