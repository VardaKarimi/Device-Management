package screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import component.*
import database.httpClient.*
import kotlinx.coroutines.runBlocking
import navigations.NavController

@Composable
fun AssignDeviceScreen(
    navController: NavController,
    ) {

    var options: List<String> by remember { mutableStateOf(emptyList()) }
    var selectedOption by remember { mutableStateOf("") }

    var dNoptions: List<String> by remember { mutableStateOf(emptyList()) }
    var dNselectedOption by remember { mutableStateOf("") }

    var selectedUserId by remember { mutableStateOf("") }
    var selectedDeviceId by remember { mutableStateOf("") }

    var users by remember { mutableStateOf(emptyList<User>()) }
    var devices by remember { mutableStateOf(emptyList<Device>()) }

    users = remember { runBlocking { fetchUsers() }  }
    devices = remember { runBlocking { fetchDevices() } }



    val scrollState = rememberScrollState()
    Header(navController)
    Container {
        HeaderTitle(Screen.AssignDeviceScreen.label)
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .width(400.dp)
                    .verticalScroll(state = scrollState, enabled = true),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                // User Name
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "User Name",
                        style = MaterialTheme.typography.h6,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.weight(0.3f)
                    )



                    LaunchedEffect(Unit) {
                        val users: List<User> = fetchUsers()
                        val devices: List<Device> = fetchDevices()
                        val userOptions = users.map { "${it.userfirstName} ${it.userlastName}" }
                        val deviceOptions = devices.map { it.deviceType + " : " + it.deviceName }
                        options = userOptions
                        dNoptions = deviceOptions
                        selectedOption = userOptions.firstOrNull() ?: ""
                        dNselectedOption = deviceOptions.firstOrNull() ?: ""
                    }

                    //user DropDown
                    DropdownMenuCommonComponent(
                        options = options,
                        selectedOption = selectedOption,
                        onOptionSelected = {  option ->
                            selectedOption = option
                            val user = users.find { "${it.userfirstName} ${it.userlastName}" == option }
                            selectedUserId = user?.id ?: ""
                        }
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Model name dropdowm
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Model Name",
                        style = MaterialTheme.typography.h6,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.weight(0.2f)
                    )

                    DropdownMenuCommonComponent(
                        options = dNoptions,
                        selectedOption = dNselectedOption,
                        onOptionSelected = { option ->
                            dNselectedOption = option
                            val device = devices.find { it.deviceType + " : " + it.deviceName == option }
                            selectedDeviceId = device?.id ?: ""
                        }
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                CustomButton(
                    onClick = {
                        runBlocking {
                        assignDevice(selectedUserId,selectedDeviceId)
                        navController.navigate(Screen.HomeScreen.name)
                    }
                              },
                    text = "Assign",
                    contentColor = Color.White
                )


            }
        }
    }

}
