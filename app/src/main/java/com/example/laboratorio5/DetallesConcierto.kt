package com.example.laboratorio5

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme

@Composable
fun DetallesConcierto(concierto: String, lugar: String) {
    Column {
        Text(text = "Concierto de $concierto", style = MaterialTheme.typography.h4)
        Text(text = "Hora: 7 - 10 pm")
        Text(text = "Lugar: $lugar")
        Text(text = "Fecha: 24 de Octubre del 2024")
        Text(text = "Detalles: Este es el concierto de $concierto que se dará el 24 de Octubre, no te lo pierdas, estará muy bueno.")
    }
}

@Preview(showBackground = true)
@Composable
fun DetallesConciertoPreview() {
    DetallesConcierto(concierto = "Manolo", lugar = "UVG")
}
