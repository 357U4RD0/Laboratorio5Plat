package com.example.laboratorio5

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme

@Composable
fun PantLugares(navController: NavController, concierto: String) {
    val lugares = listOf("UVG", "URL", "USAC", "UNIS", "UPANA", "Cayalá")
    Column {
        Text(text = "Lugares para el concierto de $concierto", style = MaterialTheme.typography.h4)
        lugares.forEach { lugar ->
            Button(onClick = { navController.navigate("detalles/$concierto/$lugar") }) {
                Text(text = lugar)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantLugaresPreview() {
    PantLugares(navController = rememberNavController(), concierto = "Manolo")
}
