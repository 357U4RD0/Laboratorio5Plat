package com.example.Laboratorio5_6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.Laboratorio5_6.ui.theme.Lab5AngeMeridaTheme
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab5AngeMeridaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProfileScreen(navController = rememberNavController())
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Mi Perfil") },
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                ProfileHeader()
                Spacer(modifier = Modifier.height(16.dp))
                ProfileOptions(navController)
            }
        }
    )
}

@Composable
fun ProfileHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .background(color = Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.perfil),
            contentDescription = "Imagen de Perfil",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .background(color = Color.Gray, shape = CircleShape)
                .clip(shape = CircleShape)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Estuardo Castro",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )
    }
}

@Composable
fun ProfileOptions(navController: NavHostController) {
    val options = listOf(
        ProfileOption("Editar Perfil", Icons.Default.Edit),
        ProfileOption("Restablecer Contraseña", Icons.Default.Lock),
        ProfileOption("Notificaciones", Icons.Default.Notifications),
        ProfileOption("Favoritos", Icons.Default.Star)
    )

    Column {
        options.forEach { option ->
            if (option.title == "Notificaciones") {
                NotificationOptionItem(option)
            } else {
                ProfileOptionItem(option, navController)
            }
            Divider(color = Color.Gray.copy(alpha = 0.5f))
        }
    }
}

@Composable
fun ProfileOptionItem(option: ProfileOption, navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                // Navegar a la pantalla correspondiente
                when (option.title) {
                    "Editar Perfil" -> navController.navigate("editProfile") // Asegúrate de definir la ruta en tu NavHost
                    "Restablecer Contraseña" -> navController.navigate("resetPassword")
                    "Favoritos" -> navController.navigate("favorites")
                }
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = option.icon,
            contentDescription = option.title,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = option.title, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun NotificationOptionItem(option: ProfileOption) {
    var isChecked by remember { mutableStateOf(true) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = option.icon,
            contentDescription = option.title,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = option.title,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        Switch(
            checked = isChecked,
            onCheckedChange = { isChecked = it }
        )
    }
}

data class ProfileOption(val title: String, val icon: ImageVector)

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    Lab5AngeMeridaTheme {
        ProfileScreen(navController = rememberNavController())
    }
}
