package com.example.healthlylife.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.healthlylife.R


@Composable
fun RecipesScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF181414))
    ) {
        Spacer(modifier = Modifier.height(60.dp))


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
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(60.dp))


            Text(
                text = "Breakfast",
                fontSize = 26.sp,
                fontFamily = damionFontFamily,
                color = Color.White
            )


            Spacer(modifier = Modifier.height(20.dp))

            RecipeImageList(navController = navController, images = listOf(R.drawable.granolafruits1, R.drawable.breakfastburrito2, R.drawable.greensmoothie3))

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Dinner",
                fontSize = 26.sp,
                fontFamily = damionFontFamily,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(20.dp))

            RecipeImageList(navController = navController, images = listOf(R.drawable.vietnamchicken4, R.drawable.pasta5, R.drawable.pancakes6))


            Spacer(modifier = Modifier.height(30.dp))


            Text(
                text = "Lunch",
                fontSize = 26.sp,
                fontFamily = damionFontFamily,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(20.dp))

            RecipeImageList(navController = navController, images = listOf(R.drawable.airfryerpotato7, R.drawable.steak8, R.drawable.fishpie9))
        }
    }
}

@Composable
fun RecipeImageList(navController: NavController, images: List<Int>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(images) { imageId ->
            Box(
                modifier = Modifier
                    .width(150.dp)
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable {
                        navController.navigate("recipes_detail/$imageId")
                    }
            ) {
                Image(
                    painter = painterResource(id = imageId),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}