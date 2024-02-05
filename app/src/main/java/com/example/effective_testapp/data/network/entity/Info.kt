package com.example.effective_testapp.data.network.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

    @Serializable
    data class Info(
        @SerialName("title")
        val title: String,
        @SerialName("value")
        val value: String
    )