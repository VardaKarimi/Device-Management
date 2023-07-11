package database.httpClient

import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*

@OptIn(InternalAPI::class)
suspend fun handleUpdateDevice(id:String,
                               deviceType: String,
                               deviceName: String,
                               manufacturer: String,
                               operatingSystem: String,
                               processor: String,
                               ram: String,
                               rom: String,
                               availableDevice: String):String{
    val response = client.request{
        method = HttpMethod.Post
        url("http://localhost:3000/updateDevice")
        body = FormDataContent(Parameters.build {
            append("id",id)
            append("deviceType", deviceType)
            append("deviceName", deviceName)
            append("manufacturer", manufacturer)
            append("operatingSystem", operatingSystem)
            append("processor", processor)
            append("ram", ram)
            append("rom", rom)
            append("availableDevice", availableDevice)
        })
    }
    return if (response.status.value == 200) {
        response.bodyAsText()
    } else {
        "failed to update device data"
    }


}