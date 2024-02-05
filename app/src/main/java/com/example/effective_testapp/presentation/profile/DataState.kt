package com.example.effective_testapp.presentation.profile

import com.example.effective_testapp.data.bd.UserData

sealed class DataState {
    object LoadingUser : DataState()
    data class SuccessUser(val user: UserData) : DataState()
    data class ErrorUser(val errorMessage: String) : DataState()
}