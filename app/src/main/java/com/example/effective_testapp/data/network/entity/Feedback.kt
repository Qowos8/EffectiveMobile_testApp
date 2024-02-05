package com.example.effective_testapp.data.network.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Feedback(
    @SerialName("count")
    val count: Int,
    @SerialName("rating")
    val rating: Float
)