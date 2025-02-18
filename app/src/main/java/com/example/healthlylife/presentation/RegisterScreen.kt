package com.example.healthlylife.presentation

import android.widget.Toast
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.healthlylife.R
import com.example.healthlylife.auth.AuthState
import com.example.healthlylife.components.CustomButton
import com.example.healthlylife.components.CustomTextField
import com.example.healthlylife.viewmodel.RegisterScreenViewModel
import com.example.healthlylife.viewmodel.SharedViewModel

@Composable
fun RegisterScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel = hiltViewModel(),
    registerViewModel: RegisterScreenViewModel = hiltViewModel()
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    val authState by sharedViewModel.authState.observeAsState(AuthState.Loading)
    val context = LocalContext.current


    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.PasswordMismatch -> {
                val errorMessage = (authState as AuthState.PasswordMismatch).message
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }
            is AuthState.UserExists -> {
                val errorMessage = (authState as AuthState.UserExists).message
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }
            else -> Unit
        }
    }


    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0xFF181414))
    )
    {

            IconButton(
                onClick = { navController.navigate("start") },
                modifier = Modifier.align(Alignment.TopStart).padding(horizontal = 30.dp, vertical = 50.dp)
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

                Spacer(modifier = Modifier.height(50.dp))

                Image(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = "logo",
                )
                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = "Create Account",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.White,
                    fontFamily = damionFontFamily
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 58.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                    CustomTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = "Email",
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Email,
                                contentDescription = "Email Icon",
                                tint = Color(0xFF8692F7)
                            )
                        }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    CustomTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = "Password",
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Lock,
                                contentDescription = "Password Icon",
                                tint = Color(0xFF8692F7)
                            )
                        }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    CustomTextField(
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        label = "Confirm Password",
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.ic_baseline_password_24),
                                tint = Color(0XFF8692F7),
                                contentDescription = "Confirm Password Icon"
                            )
                        }
                    )

                    Spacer(modifier = Modifier.height(50.dp))

                    CustomButton(
                        text = "REGISTER",
                        onClick = {
                                registerViewModel.signup(email, password, confirmPassword, sharedViewModel)
                        },
                        enabled = authState != AuthState.Loading,
                        textColor = Color.Black
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        buildAnnotatedString {
                            append("Already have an account ? ")
                            withStyle(
                                style = SpanStyle(
                                    color = Color(0xFF32CD32),
                                    fontWeight = FontWeight.Light
                                )
                            ) {
                                append("Log In")
                            }
                        },
                        style = TextStyle(fontSize = 14.sp, color = Color.White),
                        fontFamily = FontFamily.SansSerif,
                        modifier = Modifier.clickable {
                            navController.navigate("login")
                        }
                    )
                }
            }
        }
