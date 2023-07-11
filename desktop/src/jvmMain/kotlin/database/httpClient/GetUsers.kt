package database.httpClient

import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json


@Serializable
data class User(val id:String,val userfirstName: String,val userlastName:String,val userEmail:String)

suspend fun fetchUsers(): List<User>{
    val response : HttpResponse = client.get("http://localhost:3000/users")
    val responseBody = response.bodyAsText()
    val json = Json { ignoreUnknownKeys = true }
    val userList = json.decodeFromString<List<User>>(responseBody)
    return userList
}


