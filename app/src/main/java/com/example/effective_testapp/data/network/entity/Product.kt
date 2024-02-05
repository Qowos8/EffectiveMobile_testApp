package com.example.effective_testapp.data.network.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("subtitle")
    val subtitle: String,
    @SerialName("price")
    val price: Price,
    @SerialName("feedback")
    val feedback: Feedback,
    @SerialName("tags")
    val tags: List<String>,
    @SerialName("available")
    val available: Int,
    @SerialName("description")
    val description: String,
    @SerialName("info")
    val info: List<Info>,
    @SerialName("ingredients")
    val ingredients: List<String>
)