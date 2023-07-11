package navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import navigations.rememberNavController
import screens.*
import screens.homeScreen.HomeScreen
import java.util.prefs.Preferences

@Composable
fun Navigation() {
    val screens = Screen.values().toList()
    val navController by rememberNavController(Screen.LogInScreen.name)
    val currentScreen by remember {
        navController.currentScreen
    }
    val storedEmail = remember { mutableStateOf("") }
    val preferences = Preferences.userRoot().node("com.example.myapplication") // Replace with your application package name
    storedEmail.value = preferences.get("userEmail", "")

    LaunchedEffect(storedEmail.value) {
        if (storedEmail.value.isNotEmpty()) {
            navController.navigate(Screen.HomeScreen.name)
        }
    }
    Surface(
        modifier = Modifier.background(color = MaterialTheme.colors.background)
    ) {
        Box(Modifier.fillMaxSize()) {
            if (currentScreen != Screen.LogInScreen.name) {
                Row(
                    Modifier.fillMaxSize().background(Color(0xFFF27374D)),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Top
                ) {
                    Column(
                        modifier = Modifier.width(150.dp).padding(vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(text="Device Manager",
                            color = Color(0xFFFDBDFEA),
                            style = MaterialTheme.typography.h5,
                            modifier = Modifier.padding(start = 20.dp, top = 8.dp),
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(12.dp))
                        screens.filterNot { it == Screen.LogInScreen }.forEach { screen ->
                            TextButton(
                                onClick = {
                                    navController.navigate(screen.name)
                                },
                                colors = ButtonDefaults.textButtonColors(
                                    contentColor = if (currentScreen == screen.name) Color(0xFFFE8AA42) else Color(0xFFFDBDFEA)
                                ),
                                modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.Start),
                                contentPadding = PaddingValues(start = 12.dp)
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = screen.icon,
                                        contentDescription = screen.label,
                                        modifier = Modifier.padding(end = 8.dp),
                                    )
                                    Text(
                                        text = screen.label,
                                        style = MaterialTheme.typography.body1
                                    )
                                }
                            }
                        }
                    }

                    Box(
                        modifier = Modifier.weight(1f)
                    ) {
                        when (currentScreen) {
                            Screen.HomeScreen.name -> {
                                HomeScreen(navController = navController)
                            }
                            Screen.AddDeviceScreen.name -> {
                                AddDeviceScreen(navController = navController)
                            }
                            Screen.AssignDeviceScreen.name -> {
                                AssignDeviceScreen(navController = navController)
                            }
                        }
                    }
                }
            } else {
                LogInScreen(navController = navController)
            }
        }
    }
}
