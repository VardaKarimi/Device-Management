package database.httpClient

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*


suspend fun Login(email:String,password:String):String{
    val response = client.request("http://localhost:3000/logIn"){
        method = HttpMethod.Post
        url {
            parameters.append("email",email)
            parameters.append("password",password)
        }
    }
    if (response.status.value==200){
        return response.bodyAsText()
    }
    else{
        return "failure"
    }
}

@OptIn(InternalAPI::class)
suspend fun addDevice(
    deviceType: String,
    deviceName: String,
    manufacturer: String,
    operatingSystem: String,
    processor: String,
    ram: String,
    rom: String,
    availableDevice: String
): String {
    val response = client.request {
        method = HttpMethod.Post
        url("http://localhost:3000/create")
        body = FormDataContent(Parameters.build {
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
        "failure"
    }
}