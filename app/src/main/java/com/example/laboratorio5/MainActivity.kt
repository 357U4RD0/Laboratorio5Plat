import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()

    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            DrawerContent { destination ->
                // Navegación entre pantallas
                when (destination) {
                    "Pantalla 1" -> scaffoldState.drawerState.close()
                    "Pantalla 3" -> startActivity(Intent(this, LocationsActivity::class.java))
                    "Pantalla 4" -> startActivity(Intent(this, ProfileActivity::class.java))
                }
            }
        },
        content = {
            EventsScreen()
        }
    )
}

@Composable
fun DrawerContent(onDestinationClicked: (String) -> Unit) {
    Column {
        Text("Pantalla 1", modifier = Modifier.clickable { onDestinationClicked("Pantalla 1") })
        Text("Pantalla 3", modifier = Modifier.clickable { onDestinationClicked("Pantalla 3") })
        Text("Pantalla 4", modifier = Modifier.clickable { onDestinationClicked("Pantalla 4") })
    }
}

@Composable
fun EventsScreen() {
    Column {
        // Eventos favoritos
        Text(text = "Favoritos", style = TextStyle(fontSize = 20.sp), modifier = Modifier.padding(16.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(getFavoriteEvents()) { event ->
                EventCard(event) {
                    // Accion al seleccionar un evento favorito
                    // Navegar a detalle
                    startActivity(Intent(this, DetailActivity::class.java).apply {
                        putExtra("event", event)
                    })
                }
            }
        }

        // Todos los eventos
        Text(text = "Todos los Eventos", style = TextStyle(fontSize = 20.sp), modifier = Modifier.padding(16.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(getAllEvents()) { event ->
                EventCard(event) {
                    // Accion al seleccionar un evento
                    // Navegar a detalle
                    startActivity(Intent(this, DetailActivity::class.java).apply {
                        putExtra("event", event)
                    })
                }
            }
        }
    }
}

@Composable
fun EventCard(event: Event, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = 4.dp
    ) {
        Column {
            Image(
                painter = painterResource(id = event.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )
            Text(text = event.title, style = TextStyle(fontSize = 18.sp), modifier = Modifier.padding(8.dp))
            Text(text = event.description, modifier = Modifier.padding(8.dp))
        }
    }
}

fun getFavoriteEvents(): List<Event> {
    // Retornar una lista de eventos favoritos predefinidos
    return listOf(/.../)
}

fun getAllEvents(): List<Event> {
    // Retornar una lista de todos los eventos predefinidos
    return listOf(/.../)
}