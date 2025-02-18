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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.healthlylife.auth.AuthState
import com.example.healthlylife.components.CustomButton
import com.example.healthlylife.viewmodel.ProfileScreenViewModel
import com.example.healthlylife.viewmodel.SharedViewModel

@Composable
fun ProfileScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel = hiltViewModel(),
    profileScreenViewModel: ProfileScreenViewModel = hiltViewModel()
) {
    val authState by sharedViewModel.authState.observeAsState(AuthState.Loading)

    val goal = profileScreenViewModel.goal.value
    val weight = profileScreenViewModel.weight.value
    val targetWeight = profileScreenViewModel.targetWeight.value
    val height = profileScreenViewModel.height.value
    val age = profileScreenViewModel.age.value
    val gender = profileScreenViewModel.gender.value
    val email = profileScreenViewModel.email.value



    LaunchedEffect(authState) {
        if (authState == AuthState.Unauthenticated) {
            navController.navigate("login")
        }
    }


    Column(
        modifier = Modifier
            .background(color = Color(0xFF181414))
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
        ) {
            Text(
                text = "Profile",
                color = Color.White,
                fontFamily = alkatrFontFamily,
                fontSize = 24.sp,
                modifier = Modifier.align(Alignment.TopCenter)
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
                .clip(RoundedCornerShape(20.dp))
                .background(color = Color(0xFF292929))
                .height(300.dp)

        ) {

            Text(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 20.dp),
                text = email,
                color = Color.White,
                fontFamily = alkatrFontFamily,
                fontSize = 24.sp,
                )


            Column(
                modifier = Modifier
                    .padding(start = 20.dp, top = 70.dp)
            ) {
                Text(
                    text = "Gender",
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
                    text = "Height",
                    color = Color.Gray,
                    fontFamily = alkatrFontFamily,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(5.dp)
                )
                Text(
                    text = "Current weight",
                    color = Color.Gray,
                    fontFamily = alkatrFontFamily,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(5.dp)
                )
                Text(
                    text = "Target weight",
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
                    .padding(start = 20.dp, top = 70.dp, end = 20.dp)
            ) {
                Text(
                    text = gender,
                    color = Color.White,
                    fontFamily = alkatrFontFamily,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(5.dp)
                        .align(Alignment.End)
                )
                Text(
                    text = age.toString(),
                    color = Color.White,
                    fontFamily = alkatrFontFamily,
                    modifier = Modifier
                        .padding(5.dp)
                        .align(Alignment.End)
                )
                Text(
                    text = height.toString(),
                    color = Color.White,
                    fontFamily = alkatrFontFamily,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(5.dp)
                        .align(Alignment.End)
                )
                Text(
                    text = weight.toString(),
                    color = Color.White,
                    fontFamily = alkatrFontFamily,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(5.dp)
                        .align(Alignment.End)
                )
                Text(
                    text = targetWeight.toString(),
                    color = Color.White,
                    fontFamily = alkatrFontFamily,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(5.dp)
                        .align(Alignment.End)
                )
                Text(
                    text = goal,
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
            onClick = { profileScreenViewModel.signOut(sharedViewModel) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 100.dp)

        )
    }
}