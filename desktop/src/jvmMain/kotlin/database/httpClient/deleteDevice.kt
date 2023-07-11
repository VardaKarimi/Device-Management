package database.httpClient

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

suspend fun deleteDevice(deviceId:String):String{
    val response = client.request{
        method = HttpMethod.Delete
        url("http://localhost:3000/deleteDevice/$deviceId")
    }
    return if (response.status.value == 200){
        response.bodyAsText()
    }else{
        "failed to delete Device"
    }

}