package component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import database.httpClient.client
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json


@Serializable
data class DeviceDetail(
    val availableDevice: String,
    val deviceName: String,
    val deviceType: String,
    val manufacturer: String,
    val operatingSystem: String,
    val processor: String,
    val ram: String,
    val rom: String
)


suspend fun getDeviceDetail(id: String): DeviceDetail {
    val url = "http://localhost:3000/devices/$id"
    val response: HttpResponse = client.get(url)
    val responseBody = response.bodyAsText()
    val deviceDetails = Json.decodeFromString<DeviceDetail>(responseBody)
    return deviceDetails
}

@Composable
fun onDeviceClicked(deviceId: String) {
    var isLoading by remember { mutableStateOf(true) }
    var deviceDetails = runBlocking { getDeviceDetail(deviceId) }

    LaunchedEffect(deviceId) {
        isLoading = true
        deviceDetails = getDeviceDetail(deviceId)
        isLoading = false
    }

    if (isLoading) {
        // Show the loader while loading
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = Color.Black)
        }
    }else {
        deviceDetails?.let { details ->
            Card(
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp),
                backgroundColor = Color(0xFFFDBDFEA),
                elevation = 4.dp
            ) {

                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Device Type:", style = TextStyle(fontWeight = FontWeight.Bold))
                    Text(
                        deviceDetails.deviceType,
                        modifier = Modifier.padding(start = 8.dp, bottom = 2.dp)
                    )
                    Text("Device Name:", style = TextStyle(fontWeight = FontWeight.Bold))
                    Text(
                        deviceDetails.deviceName,
                        modifier = Modifier.padding(start = 8.dp, bottom = 2.dp)
                    )
                    Text("Manufacturer:", style = TextStyle(fontWeight = FontWeight.Bold))
                    Text(
                        deviceDetails.manufacturer,
                        modifier = Modifier.padding(start = 8.dp, bottom = 2.dp)
                    )
                    Text("Available Devices:", style = TextStyle(fontWeight = FontWeight.Bold))
                    Text(
                        deviceDetails.availableDevice,
                        modifier = Modifier.padding(start = 8.dp, bottom = 2.dp)
                    )
                    Text("Operating System:", style = TextStyle(fontWeight = FontWeight.Bold))
                    Text(
                        deviceDetails.operatingSystem,
                        modifier = Modifier.padding(start = 8.dp, bottom = 2.dp)
                    )
                    Text("Processor:", style = TextStyle(fontWeight = FontWeight.Bold))
                    Text(
                        deviceDetails.processor,
                        modifier = Modifier.padding(start = 8.dp, bottom = 2.dp)
                    )
                    Text("RAM Memory:", style = TextStyle(fontWeight = FontWeight.Bold))
                    Text(
                        deviceDetails.ram,
                        modifier = Modifier.padding(start = 8.dp, bottom = 2.dp)
                    )
                    Text("ROM Memory:", style = TextStyle(fontWeight = FontWeight.Bold))
                    Text(
                        deviceDetails.rom,
                        modifier = Modifier.padding(start = 8.dp, bottom = 2.dp)
                    )

                }
            }
        }
    }


}