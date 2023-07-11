package screens.homeScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import component.Container
import component.Header
import component.HeaderTitle
import database.httpClient.Device
import database.httpClient.fetchDevices
import navigations.NavController
import screens.Screen

@Composable
fun HomeScreen(navController: NavController) {
    var devices by remember { mutableStateOf(emptyList<Device>()) }
    var selectedDeviceType by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(selectedDeviceType) {
        isLoading = true
        devices = fetchDevices()
        isLoading = false
    }

    Header(navController)
    Container {
        HeaderTitle(Screen.HomeScreen.label)
        if (isLoading) {
            CircularProgressIndicator(color = Color.Black, modifier = Modifier.padding(250.dp))
        } else {
            Column(Modifier.fillMaxSize()) {
                Column(
                    Modifier
                        .width(500.dp)
                        .align(Alignment.CenterHorizontally)
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                ) {
                    DropDownContent(
                        devices = devices,
                        selectedDeviceType = selectedDeviceType,
                        onDeviceTypeSelected = { deviceType ->
                            // Update the selected device type state
                            selectedDeviceType = deviceType
                        },
                        navController
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}
