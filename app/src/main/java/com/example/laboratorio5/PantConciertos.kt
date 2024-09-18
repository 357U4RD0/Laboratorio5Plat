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
fun PantConciertos(navController: NavController) {
    val conciertos = listOf("Manolo", "Paco", "Juan")
    Column {
        Text(text = "Próximos Conciertos", style = MaterialTheme.typography.h4)
        conciertos.forEach { concierto ->
            Button(onClick = { navController.navigate("lugares/$concierto") }) {
                Text(text = concierto)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantConciertosPreview() {
    PantConciertos(navController = rememberNavController())
}
