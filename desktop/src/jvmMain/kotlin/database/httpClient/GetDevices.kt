package database.httpClient

import UpdateDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import component.ConfirmationDialog
import component.onDeviceClicked
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.runBlocking


import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import navigations.NavController


@Serializable
data class Device(
    val availableDevice: String,
    val deviceName: String,
    val deviceType: String,
    val id: String,
    val manufacturer: String,
    val operatingSystem: String,
    val processor: String,
    val ram: String,
    val rom: String
)


suspend fun fetchDevices(): List<Device> {
    val response :HttpResponse = client.get("http://localhost:3000/devices")
    val responseBody = response.bodyAsText()
    val json = Json { ignoreUnknownKeys = true }
    val deviceList = json.decodeFromString<List<Device>>(responseBody)
    return deviceList
}


@Composable
fun PrintDevices(initialDevices: List<Device>, selectedDeviceType: String,navController: NavController) {
    val devices by remember { derivedStateOf { filterDevices(initialDevices, selectedDeviceType) } }

    Card {
        LazyColumn {
            items(devices) { device ->
                DeviceItem(device)
            }
        }
    }

}

@Composable
fun DeviceItem(device: Device) {
    var detailScreen = remember { mutableStateOf(false) }
    var showConfirmDialog by remember { mutableStateOf(false) }
    val showUpdateDialog = remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .background(Color(0xFFF27374D))
            .clickable {
                detailScreen.value = !detailScreen.value

            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = device.deviceName,
            style = TextStyle(Color(0xFFFDBDFEA)),
            modifier = Modifier.padding(start = 8.dp, end = 8.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = { showUpdateDialog.value = true }
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit",
                tint = Color(0xFFFE8AA42),
                modifier = Modifier.size(20.dp)
            )
        }
        IconButton(
            onClick = { showConfirmDialog = true }
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete",
                tint = Color(0xFFFE8AA42),
                modifier = Modifier.size(20.dp)
            )
        }
    }
    if(showConfirmDialog){
        ConfirmationDialog(
            title = "Delete Device",
            message = "Are You sure you want to delete ${device.deviceName}?",
            onConfirm = {
                runBlocking {
                    deleteDevice(device.id)
                }
                showConfirmDialog = false
                        },
            onCancel = {showConfirmDialog=false}
        )
    }

    if (showUpdateDialog.value) {
        UpdateDialog(
            title = "Update Device",
            deviceDetails = Device(
                id = device.id,
                deviceType = device.deviceType,
                deviceName = device.deviceName,
                manufacturer = device.manufacturer,
                availableDevice = device.availableDevice,
                operatingSystem = device.operatingSystem,
                processor = device.processor,
                ram = device.ram,
                rom = device.rom
            ),
            onDone = { updatedDeviceDetails ->
                runBlocking {
                    handleUpdateDevice(
                        id = device.id,
                        deviceType = updatedDeviceDetails.deviceType,
                        deviceName = updatedDeviceDetails.deviceName,
                        manufacturer = updatedDeviceDetails.manufacturer,
                        operatingSystem = updatedDeviceDetails.operatingSystem,
                        processor = updatedDeviceDetails.processor,
                        ram = updatedDeviceDetails.ram,
                        rom = updatedDeviceDetails.rom,
                        availableDevice = updatedDeviceDetails.availableDevice
                    )
                }
                showUpdateDialog.value = false // Close the dialog
            }
        ) { showUpdateDialog.value = false } // Close the dialog
    }

    if(detailScreen.value){
        Row {
            onDeviceClicked(device.id)

            Spacer(modifier = Modifier.width(Dp(16f)))

            Column {
                Text("This user/s have ${device.deviceName}", modifier = Modifier.padding(start = 6.dp, bottom = 2.dp))
                getUserAssignedDevice(device.id)
            }
        }

    }
}


private fun filterDevices(initialDevices: List<Device>, selectedDeviceType: String): List<Device> {
    return if (selectedDeviceType.isNotEmpty()) {
        initialDevices.filter { it.deviceType.equals(selectedDeviceType, ignoreCase = true) }
    } else {
        initialDevices
    }
}




