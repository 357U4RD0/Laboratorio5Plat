package com.example.Laboratorio5_6

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.Laboratorio5_6.ui.theme.Celeste
import com.example.Laboratorio5_6.ui.theme.Lab5AngeMeridaTheme
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab5AngeMeridaTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController, startDestination = "main") {
                        composable("main") { MainActivityContent(navController) }
                        composable("concertInfo") { ConcertInfoScreen() }
                        composable("placesList") { PlacesListScreen() }
                        composable("eventDetail") { EventDetailScreen() }
                        composable("profile") { ProfileScreen(navController) }
                    }
                }
            }
        }
    }
}

@Composable
fun MainActivityContent(navController: NavHostController) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        CelesteButton(
            text = "Ir a Información de Conciertos",
            onClick = {
                context.startActivity(Intent(context, ConcertInfoActivity::class.java))
            }
        )
        CelesteButton(
            text = "Ir a Lista de Lugares",
            onClick = {
                context.startActivity(Intent(context, PlacesListActivity::class.java))
            }
        )
        CelesteButton(
            text = "Ir a Detalles del Evento",
            onClick = {
                context.startActivity(Intent(context, EventDetailActivity::class.java))
            }
        )
        CelesteButton(
            text = "Ir a Perfil",
            onClick = { navController.navigate("profile") }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConcertInfoScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Información de Conciertos") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(), // Usando mediumTopAppBarColors
                actions = {}
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            Text("Aquí va la información de los conciertos.")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlacesListScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Lugares") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(), // Usando mediumTopAppBarColors
                actions = {}
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            Text("Aquí va la lista de lugares.")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventDetailScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalles del Evento") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(), // Usando mediumTopAppBarColors
                actions = {}
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            Text("Aquí van los detalles del evento.")
        }
    }
}

@Composable
fun CelesteButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Celeste,
            contentColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(text = text)
    }
}
