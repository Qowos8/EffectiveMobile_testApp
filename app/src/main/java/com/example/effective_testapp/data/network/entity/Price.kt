package com.example.effective_testapp.data.network.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Price(
    @SerialName("price")
    val price: Int,
    @SerialName("discount")
    val discount: Int,
    @SerialName("priceWithDiscount")
    val priceWithDiscount: Int,
    @SerialName("unit")
    val unit: Unit
)