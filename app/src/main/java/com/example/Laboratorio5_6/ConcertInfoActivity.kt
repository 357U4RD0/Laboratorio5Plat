package com.example.Laboratorio5_6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.Laboratorio5_6.ui.theme.Lab5AngeMeridaTheme
import kotlinx.coroutines.launch

class ConcertInfoActivity(navController: NavHostController) : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab5AngeMeridaTheme {
                ConcertInfoNavHost()
            }
        }
    }
}

@Composable
fun ConcertInfoNavHost() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "concert_info") {
        composable("concert_info") { ConcertInfoLayout(navController) }
        composable("event_detail/{concertTitle}") { backStackEntry ->
            val concertTitle = backStackEntry.arguments?.getString("concertTitle")
            concertTitle?.let { EventDetailScreen(it) }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConcertInfoLayout(navController: NavController) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(navController)
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("TodoEventos") },
                    actions = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(
                                Icons.Default.MoreVert,
                                contentDescription = "Menú"
                            )
                        }
                    },
                    modifier = Modifier.background(color = Color(0xFF6200EE)),
                )
            },
            content = { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)) {
                    ConcertInfoScreen(navController)
                }
            }
        )
    }
}

@Composable
fun ConcertInfoScreen(navController: NavController) {
    val favoriteConcerts = remember { getSampleConcerts() }
    val allConcerts = remember { getSampleConcerts() }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Text(
                text = "\n\nTus Favoritos",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        items(favoriteConcerts.chunked(2)) { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                rowItems.forEach { concert ->
                    ConcertCard(concert, Modifier.weight(1f)) {
                        navController.navigate("event_detail/${concert.title}") // Navegar a detalles del evento
                    }
                }
                if (rowItems.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }

        item {
            Text(
                text = "Todos los Conciertos",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        items(allConcerts.chunked(2)) { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                rowItems.forEach { concert ->
                    ConcertCard(concert, Modifier.weight(1f)) {
                        navController.navigate("event_detail/${concert.title}") // Navegar a detalles del evento
                    }
                }
                if (rowItems.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun ConcertCard(concert: Concert, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable(onClick = onClick) // Manejo de clic para navegar
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = concert.imageRes),
                contentDescription = concert.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = concert.title, fontWeight = FontWeight.Bold)
            Text(text = concert.supportingText, color = Color.Gray)
        }
    }
}

data class Concert(
    val title: String,
    val supportingText: String,
    val imageRes: Int
)

fun getSampleConcerts(): List<Concert> {
    return listOf(
        Concert("Concierto 1", "Detalles del concierto", R.drawable.conc1or),
        Concert("Concierto 2", "Detalles del concierto", R.drawable.conc2or),
        Concert("Concierto 3", "Detalles del concierto", R.drawable.conc3or),
        Concert("Concierto 4", "Detalles del concierto", R.drawable.concierto)
    )
}

@Composable
fun DrawerContent(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp)
            .background(color = Color.White)
            .width(200.dp)
    ) {
        Text(
            text = "Menú de Navegación",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(text = "Perfil", modifier = Modifier.padding(8.dp))
        Text(text = "Eventos", modifier = Modifier.padding(8.dp))
        Text(text = "Ajustes", modifier = Modifier.padding(8.dp))
        Text(
            text = "Detalles de Evento",
            modifier = Modifier.padding(8.dp).clickable {
                navController.navigate("event_detail/Concierto 1") // Ejemplo de navegación desde el menú
            }
        )
    }
}

@Composable
fun EventDetailScreen(concertTitle: String) {
    // Aquí se debe implementar la UI para mostrar los detalles del concierto
    // Por ejemplo, puedes mostrar un título y una descripción basada en el título del concierto
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Detalles del $concertTitle", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        // Añade más detalles del concierto aquí
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun VistaPreviaConcertInfoLayout() {
    Lab5AngeMeridaTheme {
        ConcertInfoLayout(rememberNavController())
    }
}
