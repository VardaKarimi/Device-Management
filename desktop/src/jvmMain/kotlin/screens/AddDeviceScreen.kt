package screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import component.Container
import component.CustomButton
import component.Header
import component.HeaderTitle
import database.httpClient.addDevice
import kotlinx.coroutines.runBlocking
import navigations.NavController


@Suppress("SuspiciousIndentation")

@Composable
fun AddDeviceScreen(
    navController: NavController

    ) {
    var deviceType by remember { mutableStateOf("") }
    var deviceName by remember { mutableStateOf("") }
    var manufacturer by remember { mutableStateOf("") }
    var operatingSystem by remember { mutableStateOf("") }
    var processor by remember { mutableStateOf("") }
    var ramMemory by remember { mutableStateOf("") }
    var romMemory by remember { mutableStateOf("") }
    var availableDevices by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    var isTextFieldClicked by remember { mutableStateOf(false) }

    Header(navController)
    Container {
        HeaderTitle(Screen.AddDeviceScreen.label)
        Box(
            modifier = Modifier.fillMaxSize().padding(start = 30.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 50.dp)
                    .width(500.dp)
                    .verticalScroll(state = scrollState, enabled = true),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Spacer(modifier = Modifier.height(50.dp))

                // DeviceType
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Device Type",
                        style = MaterialTheme.typography.subtitle1,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.weight(0.3f)
                    )
                    OutlinedTextField(
                        value = deviceType,
                        shape = RoundedCornerShape(5.dp),
                        onValueChange = { deviceType = it },
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .clickable { isTextFieldClicked = true },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = if (isTextFieldClicked) Color(0xFFFDBDFEA) else Color(0xFFF27374D)
                        )
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // DeviceName
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Device Name",
                        style = MaterialTheme.typography.subtitle1,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.weight(0.3f)
                    )
                    OutlinedTextField(
                        value = deviceName,
                        shape = RoundedCornerShape(5.dp),
                        onValueChange = { deviceName = it },
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .clickable { isTextFieldClicked = true },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = if (isTextFieldClicked) Color(0xFFFDBDFEA) else Color(0xFFF27374D)
                        )
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Manufacturer
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Manufacturer",
                        style = MaterialTheme.typography.subtitle1,
                        textAlign = TextAlign.Start,
                    modifier = Modifier.weight(0.2f)
                    )
                    OutlinedTextField(
                        value = manufacturer,
                        shape = RoundedCornerShape(5.dp),
                        onValueChange = { manufacturer = it },
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .clickable { isTextFieldClicked = true },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = if (isTextFieldClicked) Color(0xFFFDBDFEA) else Color(0xFFF27374D)
                        )
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Operating System
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Operating System",
                        style = MaterialTheme.typography.subtitle1,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.weight(0.3f)
                    )
                    OutlinedTextField(
                        value = operatingSystem,
                        shape = RoundedCornerShape(5.dp),
                        onValueChange = { operatingSystem = it },
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .clickable { isTextFieldClicked = true },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = if (isTextFieldClicked) Color(0xFFFDBDFEA) else Color(0xFFF27374D)
                        )
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Processor
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Processor",
                        style = MaterialTheme.typography.subtitle1,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.weight(0.3f)
                    )
                    OutlinedTextField(
                        value = processor,
                        shape = RoundedCornerShape(5.dp),
                        onValueChange = { processor = it },
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .clickable { isTextFieldClicked = true },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = if (isTextFieldClicked) Color(0xFFFDBDFEA) else Color(0xFFF27374D)
                        )
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // RAM
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "RAM",
                        style = MaterialTheme.typography.subtitle1,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.weight(0.3f)
                    )
                    OutlinedTextField(
                        value = ramMemory,
                        shape = RoundedCornerShape(5.dp),
                        onValueChange = { ramMemory = it },
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .clickable { isTextFieldClicked = true },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = if (isTextFieldClicked) Color(0xFFFDBDFEA) else Color(0xFFF27374D)
                        )
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // ROM
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "ROM",
                        style = MaterialTheme.typography.subtitle1,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.weight(0.3f)
                    )
                    OutlinedTextField(
                        value = romMemory,
                        shape = RoundedCornerShape(5.dp),
                        onValueChange = { romMemory = it },
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .clickable { isTextFieldClicked = true },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = if (isTextFieldClicked) Color(0xFFFDBDFEA) else Color(0xFFF27374D)
                        )
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Available Devices
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Available Devices",
                        style = MaterialTheme.typography.subtitle1,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.weight(0.3f)
                    )
                    OutlinedTextField(
                        value = availableDevices,
                        shape = RoundedCornerShape(5.dp),
                        onValueChange = { availableDevices = it },
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .clickable { isTextFieldClicked = true },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = if (isTextFieldClicked) Color(0xFFFDBDFEA) else Color(0xFFF27374D)
                        )
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))


                CustomButton(
                    onClick = { val deviceType = deviceType.trim()
                        val deviceName = deviceName.trim()
                        val manufacturer = manufacturer.trim()
                        val operatingSystem = operatingSystem.trim()
                        val processor = processor.trim()
                        val ram = ramMemory.trim()
                        val rom = romMemory.trim()
                        val availableDevice = availableDevices.trim()

                        if(deviceType.isNotEmpty() && deviceName.isNotEmpty() && manufacturer.isNotEmpty() && availableDevice.isNotEmpty()){
                            val result = runBlocking { addDevice(deviceType,deviceName,manufacturer,operatingSystem,processor,ram,rom,availableDevice) }
                            println(result)
                            navController.navigateBack()
                        }
                        else{
                            println("error storing devices")
                        } },
                    text = "Add Device",
                    contentColor = Color.White
                )


            }
        }
    }
    }

