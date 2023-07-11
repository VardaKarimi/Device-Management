package database.httpClient

import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*

@OptIn(InternalAPI::class)
suspend fun assignDevice(userID:String, modelId:String):String{
    val response = client.request{
        method = HttpMethod.Post
        url("http://localhost:3000/createDeviceUser")
        body = FormDataContent(Parameters.build {
            append("userId",userID)
            append("modelId",modelId)
        })
    }
    return if (response.status.value == 200) {
        response.bodyAsText()
    } else {
        "failed to assign device"
    }
}