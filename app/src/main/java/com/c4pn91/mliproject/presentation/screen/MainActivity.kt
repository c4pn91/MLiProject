package com.c4pn91.mliproject.presentation.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.c4pn91.mliproject.common.navigation.NavHostHelper
import com.c4pn91.mliproject.common.navigation.Routes
import com.c4pn91.mliproject.presentation.theme.MLiProjectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MLiProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHostHelper(
                        navHostController = navController,
                        startDestination = Routes.SearchScreen.route
                    )
                }
            }
        }
    }

}
