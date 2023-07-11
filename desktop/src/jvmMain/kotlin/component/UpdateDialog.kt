import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import component.CustomButton
import database.httpClient.Device


@Composable
fun UpdateDialog(
    title: String,
    deviceDetails: Device,
    onDone: (Device) -> Unit,
    onCancel: () -> Unit
) {
    val updatedDeviceDetails = remember { mutableStateOf(deviceDetails.copy()) }
    var isTextFieldClicked by remember { mutableStateOf(false) }

    Dialog(
        onCloseRequest = onCancel,
        title = "Update Device",
    ) {
        val scrollState = rememberScrollState()
        Box(modifier = Modifier.verticalScroll(scrollState).background(Color(0xFFFDBDFEA))) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.h6
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Device Type:", modifier = Modifier.padding(4.dp))
                OutlinedTextField(
                    value = updatedDeviceDetails.value.deviceType,
                    onValueChange = {
                        updatedDeviceDetails.value =
                            updatedDeviceDetails.value.copy(deviceType = it)
                    },
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .clickable { isTextFieldClicked = true },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = if (isTextFieldClicked) Color(0xFFFDBDFEA) else Color(0xFFF27374D)
                    )
                )

                Text("Device Name:", modifier = Modifier.padding(4.dp))
                OutlinedTextField(
                    value = updatedDeviceDetails.value.deviceName,
                    onValueChange = {
                        updatedDeviceDetails.value =
                            updatedDeviceDetails.value.copy(deviceName = it)
                    },
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .clickable { isTextFieldClicked = true },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = if (isTextFieldClicked) Color(0xFFFDBDFEA) else Color(0xFFF27374D)
                    )
                )

                Text("Manufacturer:", modifier = Modifier.padding(4.dp))
                OutlinedTextField(
                    value = updatedDeviceDetails.value.manufacturer,
                    onValueChange = {
                        updatedDeviceDetails.value =
                            updatedDeviceDetails.value.copy(manufacturer = it)
                    },
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .clickable { isTextFieldClicked = true },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = if (isTextFieldClicked) Color(0xFFFDBDFEA) else Color(0xFFF27374D)
                    )
                )

                Text("Available Devices:", modifier = Modifier.padding(4.dp))
                OutlinedTextField(
                    value = updatedDeviceDetails.value.availableDevice,
                    onValueChange = {
                        updatedDeviceDetails.value =
                            updatedDeviceDetails.value.copy(availableDevice = it)
                    },
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .clickable { isTextFieldClicked = true },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = if (isTextFieldClicked) Color(0xFFFDBDFEA) else Color(0xFFF27374D)
                    )
                )

                Text("Operating System:", modifier = Modifier.padding(4.dp))
                OutlinedTextField(
                    value = updatedDeviceDetails.value.operatingSystem,
                    onValueChange = {
                        updatedDeviceDetails.value =
                            updatedDeviceDetails.value.copy(operatingSystem = it)
                    },
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .clickable { isTextFieldClicked = true },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = if (isTextFieldClicked) Color(0xFFFDBDFEA) else Color(0xFFF27374D)
                    )
                )

                Text("Processor:", modifier = Modifier.padding(4.dp))
                OutlinedTextField(
                    value = updatedDeviceDetails.value.processor,
                    onValueChange = {
                        updatedDeviceDetails.value = updatedDeviceDetails.value.copy(processor = it)
                    },
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .clickable { isTextFieldClicked = true },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = if (isTextFieldClicked) Color(0xFFFDBDFEA) else Color(0xFFF27374D)
                    )
                )

                Text("RAM Memory:", modifier = Modifier.padding(4.dp))
                OutlinedTextField(
                    value = updatedDeviceDetails.value.ram,
                    onValueChange = {
                        updatedDeviceDetails.value = updatedDeviceDetails.value.copy(ram = it)
                    },
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .clickable { isTextFieldClicked = true },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = if (isTextFieldClicked) Color(0xFFFDBDFEA) else Color(0xFFF27374D)
                    )
                )

                Text("ROM Memory:", modifier = Modifier.padding(4.dp))
                OutlinedTextField(
                    value = updatedDeviceDetails.value.rom,
                    onValueChange = {
                        updatedDeviceDetails.value = updatedDeviceDetails.value.copy(rom = it)
                    },
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .clickable { isTextFieldClicked = true },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = if (isTextFieldClicked) Color(0xFFFDBDFEA) else Color(0xFFF27374D)
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    CustomButton(
                        onClick = { onDone(updatedDeviceDetails.value) },
                        modifier = Modifier.padding(end = 8.dp),
                        text = "Done"
                    )
                    CustomButton(
                        onClick = onCancel,
                        text = "Cancel"
                    )
                }
            }
        }
    }
}
