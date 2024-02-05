package com.example.effective_testapp.data.network

import com.example.effective_testapp.data.network.entity.Product
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import io.reactivex.rxjava3.core.Single
import kotlinx.serialization.json.Json

class KtorClient: NetworkInterface {
    private val client = HttpClient(OkHttp){

        defaultRequest { url("https://run.mocky.io/") }

        install(ContentNegotiation){
            json(Json{
                ignoreUnknownKeys = true
            })
        }
    }
    override suspend fun getItems(): List<Product> {
        return client.get("v3/97e721a7-0a66-4cae-b445-83cc0bcf9010").body()
    }
}

