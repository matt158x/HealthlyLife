package com.example.healthlylife

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.healthlylife.navigation.AppNavigation
import com.example.healthlylife.ui.theme.HealthlyLifeTheme
import com.example.healthlylife.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val sharedViewModel : SharedViewModel by viewModels()
        setContent {
            enableEdgeToEdge(
                statusBarStyle = SystemBarStyle.dark(Color.Transparent.toArgb())
            )
            HealthlyLifeTheme(dynamicColor = false) {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    AppNavigation(
                        sharedViewModel = sharedViewModel
                    )
                }
            }
        }
    }
}
