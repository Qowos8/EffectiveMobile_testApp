package com.example.effective_testapp.data.network

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
/*
object RetrofitModule {
    private const val BASE_URL = "https://run.mocky.io/"
    private const val JSON_MIME_TYPE = "application/json"
    val json = Json{ignoreUnknownKeys = true}

    fun create (@Named("authToken") authToken: String): GitHubService {
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(AuthorizationInterceptor(authToken))
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory(JSON_MIME_TYPE.toMediaType()))
            .client(client)
            .build()
            .create(GitHubService::class.java)
    }
}*/