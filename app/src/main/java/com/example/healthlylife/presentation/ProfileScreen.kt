package com.example.healthlylife.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.healthlylife.components.CustomButton
import com.example.healthlylife.viewmodel.ProfileScreenViewModel
import com.example.healthlylife.viewmodel.SharedViewModel

@Composable
fun ProfileScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel = viewModel(),
    profileScreenViewModel: ProfileScreenViewModel = viewModel { ProfileScreenViewModel(sharedViewModel) }
) {


    Column(
        modifier = Modifier
            .background(color = Color(0xFF181414))
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally // Wyśrodkowanie poziome
    ) {


        Box(
            modifier = Modifier
                .fillMaxWidth() // Zapewnia szerokość dla wyśrodkowania
                .padding(top = 50.dp)
        ) {
            Text(
                text = "Profile",
                color = Color.White,
                fontFamily = alkatrFontFamily,
                fontSize = 24.sp,
                modifier = Modifier.align(Alignment.TopCenter) // Wyśrodkowanie tekstu
            )

            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 20.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .clip(RoundedCornerShape(20.dp)) // Najpierw clip
                .background(color = Color(0xFF292929)) // Potem background, żeby działał na cały zaokrąglony Box
                .height(240.dp)

        ) {

            Text(
                modifier = Modifier
                    .align(Alignment.TopCenter),
                text = "fhdhdf",
                color = Color.White,
                fontFamily = alkatrFontFamily,
                fontSize = 24.sp,
                )


            Column(
                modifier = Modifier
                    .padding(start = 20.dp, top = 40.dp)
            ) {

                Text(
                    text = "Current weight",
                    color = Color.Gray,
                    fontFamily = alkatrFontFamily,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(5.dp)
                )

                Text(
                    text = "Height",
                    color = Color.Gray,
                    fontFamily = alkatrFontFamily,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(5.dp)
                )
                Text(
                    text = "Age",
                    color = Color.Gray,
                    fontFamily = alkatrFontFamily,
                    modifier = Modifier.padding(5.dp)
                )
                Text(
                    text = "Gender",
                    color = Color.Gray,
                    fontFamily = alkatrFontFamily,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(5.dp)
                )
                Text(
                    text = "Goal",
                    color = Color.Gray,
                    fontFamily = alkatrFontFamily,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(5.dp)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 40.dp, end = 20.dp)
            ) {

                Text(
                    text = "Current weight",
                    color = Color.White,
                    fontFamily = alkatrFontFamily,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(5.dp)
                        .align(Alignment.End)
                )

                Text(
                    text = "Height",
                    color = Color.White,
                    fontFamily = alkatrFontFamily,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(5.dp)
                        .align(Alignment.End)
                )
                Text(
                    text = "Age",
                    color = Color.White,
                    fontFamily = alkatrFontFamily,
                    modifier = Modifier
                        .padding(5.dp)
                        .align(Alignment.End)
                )
                Text(
                    text = "Gender",
                    color = Color.White,
                    fontFamily = alkatrFontFamily,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(5.dp)
                        .align(Alignment.End)
                )
                Text(
                    text = "Goal",
                    color = Color.White,
                    fontFamily = alkatrFontFamily,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(5.dp)
                        .align(Alignment.End)
                )


            }
        }

        CustomButton(
            text = "Logout",
            onClick = { profileScreenViewModel.signOut() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 100.dp)

        )
    }
}