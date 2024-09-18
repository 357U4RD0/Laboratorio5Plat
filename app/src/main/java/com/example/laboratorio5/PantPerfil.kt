package com.example.laboratorio5

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme

@Composable
fun PantPerfil() {
    Column {
        Text(text = "Mi Perfil", style = MaterialTheme.typography.h4)
        // Botones para el perfil
        Button(onClick = { /* Acción para editar perfil */ }) {
            Text(text = "Editar Perfil")
        }
        Button(onClick = { /* Acción para resetear contraseña */ }) {
            Text(text = "Resetear Contraseña")
        }
        Button(onClick = { /* Acción para recibir notificaciones */ }) {
            Text(text = "Recibir Notificaciones")
        }
        Button(onClick = { /* Acción para ver eventos favoritos */ }) {
            Text(text = "Eventos Favoritos")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantPerfilPreview() {
    PantPerfil()
}
