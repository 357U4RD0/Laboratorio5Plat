package com.example.laboratorio5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AppNavigator()
            }
        }
    }
}

@Composable
fun AppNavigator() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "conciertos") {
        composable("conciertos") { PantConciertos(navController) }
        composable("lugares/{concierto}") { backStackEntry ->
            PantLugares(navController, backStackEntry.arguments?.getString("concierto") ?: "")
        }
        composable("detalles/{concierto}/{lugar}") { backStackEntry ->
            DetallesConcierto(
                backStackEntry.arguments?.getString("concierto") ?: "",
                backStackEntry.arguments?.getString("lugar") ?: ""
            )
        }
    }
}
