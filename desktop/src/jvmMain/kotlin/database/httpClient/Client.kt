package database.httpClient

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*

val client = HttpClient(CIO){
    install(HttpTimeout) {
        requestTimeoutMillis = HttpTimeout.INFINITE_TIMEOUT_MS
        connectTimeoutMillis = HttpTimeout.INFINITE_TIMEOUT_MS
        socketTimeoutMillis = HttpTimeout.INFINITE_TIMEOUT_MS
    }
    install(ContentNegotiation){
        json()
    }
}