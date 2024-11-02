package com.example.Laboratorio5_6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.Laboratorio5_6.ui.theme.Lab5AngeMeridaTheme
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController

class PlacesListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab5AngeMeridaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PlacesListScreen(navController = rememberNavController())
                }
            }
        }
    }
}

data class Place(val name: String, val location: String, val imageResId: Int)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlacesListScreen(navController: NavHostController) {
    val places = listOf(
        Place("España", "One Republic España", R.drawable.espana),
        Place("Suiza", "One Republic Suiza", R.drawable.suiza),
        Place("Australia", "One Republic Australia", R.drawable.australia)
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Lugares") },
            )
        },
        content = { padding ->
            LazyColumn(contentPadding = padding) {
                items(places) { place ->
                    PlaceItem(place = place, navController)
                }
            }
        }
    )
}

@Composable
fun PlaceItem(place: Place, navController: NavHostController) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                // Navegar a la pantalla de detalles del lugar
                navController.navigate("placeDetail/${place.name}") // Asegúrate de definir la ruta en tu NavHost
            }
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = place.imageResId),
                contentDescription = place.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(64.dp)
                    .clip(shape = CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = place.location,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = place.name,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlacesListScreenPreview() {
    Lab5AngeMeridaTheme {
        PlacesListScreen(navController = rememberNavController())
    }
}
