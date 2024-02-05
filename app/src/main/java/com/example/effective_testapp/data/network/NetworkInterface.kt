package com.example.effective_testapp.data.network

import com.example.effective_testapp.data.network.entity.Product
import io.reactivex.rxjava3.core.Single

interface NetworkInterface {
    suspend fun getItems(): List<Product>
}

interface ApiInterface {
    fun getItems(): Single<List<Product>>
}