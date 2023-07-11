package database.httpClient

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import component.DeviceDetail
import component.getDeviceDetail
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json

@kotlinx.serialization.Serializable
data class DeviceUsers(val id:String,val userId:String,val modelId:String)

suspend fun fetchDeviceUser():List<DeviceUsers>{
    val response = client.get("http://localhost:3000/deviceUser")
    val responseBody = response.bodyAsText()
    val json = Json { ignoreUnknownKeys = true }
    val deviceUserList = json.decodeFromString<List<DeviceUsers>>(responseBody)
    return deviceUserList

}
@Composable
fun getUserAssignedDevice(modelId: String) {
    val assignedUserIds = mutableListOf<String>()

    runBlocking {
        val deviceUserList = fetchDeviceUser()

        for (deviceUser in deviceUserList) {
            if (deviceUser.modelId == modelId) {
                assignedUserIds.add(deviceUser.userId)
            }
        }
    }
    for (userId in assignedUserIds) {
        val userDetails = runBlocking { getUser(userId) }
        Card(modifier = Modifier.padding(start=16.dp, bottom = 8.dp).width(200.dp), backgroundColor = Color(0xFFFDBDFEA), elevation = 4.dp) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("User First Name:", style = TextStyle(fontWeight = FontWeight.Bold))
                Text(
                    userDetails.userfirstName,
                    modifier = Modifier.padding(start = 8.dp, bottom = 2.dp)
                )
                Text("User Last Name:", style = TextStyle(fontWeight = FontWeight.Bold))
                Text(
                    userDetails.userlastName,
                    modifier = Modifier.padding(start = 8.dp, bottom = 2.dp)
                )
                Text("User Email:", style = TextStyle(fontWeight = FontWeight.Bold))
                Text(
                    userDetails.userEmail,
                    modifier = Modifier.padding(start = 8.dp, bottom = 2.dp)
                )
                Text("User Mobile:", style = TextStyle(fontWeight = FontWeight.Bold))
                Text(
                    userDetails.userMobile,
                    modifier = Modifier.padding(start = 8.dp, bottom = 2.dp)
                )
            }
        }
    }
}

@kotlinx.serialization.Serializable
data class UserDetail(val userfirstName:String,val userlastName:String,val userEmail:String,val userMobile: String)

suspend fun getUser(userId:String): UserDetail {
    val url = "http://localhost:3000/users/$userId"
    val response: HttpResponse = client.get(url)
    val responseBody = response.bodyAsText()
    val userDetails = Json.decodeFromString<UserDetail>(responseBody)
    return userDetails
}

