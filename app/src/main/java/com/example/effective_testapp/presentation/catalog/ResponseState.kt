package com.example.effective_testapp.presentation.catalog

import com.example.effective_testapp.data.network.entity.Product

sealed class ResponseState {
    object Loading : ResponseState()
    data class Success(val response: List<Product>) : ResponseState()
    data class Error(val errorMessage: String) : ResponseState()
}