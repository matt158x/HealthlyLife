package com.example.healthlylife.form


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.healthlylife.R
import com.example.healthlylife.components.CustomButton
import com.example.healthlylife.components.CustomFormButton
import com.example.healthlylife.presentation.damionFontFamily
import com.example.healthlylife.viewmodel.UserFormViewModel


@Composable
fun GoalStep(
    navController: NavController,
    viewModel: UserFormViewModel = hiltViewModel(),
    onNext: () -> Unit) {

    var selectedGoal by remember { mutableStateOf("") }
    val context = LocalContext.current


    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0xFF181414))
    )
    {

        Spacer(modifier=Modifier.height(60.dp))

        IconButton(
            onClick = { navController.navigate("start") },
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

            Spacer(modifier=Modifier.height(50.dp))

            Text(
                text = "What is your goal ?",
                fontFamily = damionFontFamily,
                color = Color.White,
                fontSize = 32.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomFormButton(
                modifier = Modifier,
                text = "GAIN WEIGHT",
                isClicked = selectedGoal == "Gain Weight",
                onClick = {
                    selectedGoal = "Gain Weight"
                    viewModel.updateGoal("Gain Weight", R.drawable.wykres1)
                }
            )

            CustomFormButton(
                modifier = Modifier,
                text = "LOSE WEIGHT",
                isClicked = selectedGoal == "Lose Weight",
                onClick = {
                    selectedGoal = "Lose Weight"
                    viewModel.updateGoal("Lose Weight", R.drawable.wykres2)
                }
            )

            CustomFormButton(
                modifier = Modifier,
                text = "MAINTAIN WEIGHT",
                isClicked = selectedGoal == "Maintain Weight",
                onClick = {
                    selectedGoal = "Maintain Weight"
                    viewModel.updateGoal("Maintain Weight", R.drawable.wykres0)
                }
            )

            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = "We use this information to calculate and provide you with \n daily personalized recommendations",
                color = Color(0xFF616161),
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(20.dp))

            CustomButton(
                onClick = {
                    if (selectedGoal.isEmpty()){
                        Toast.makeText(context, "Please select a goal to proceed", Toast.LENGTH_SHORT).show()
                    } else {
                        onNext()
                    }
                },
                text = "NEXT",
                textColor = Color.Black,
                textSize = 18.sp
            )
        }
    }
}