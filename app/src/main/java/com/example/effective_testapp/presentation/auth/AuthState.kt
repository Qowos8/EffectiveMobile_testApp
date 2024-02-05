package com.example.effective_testapp.presentation.auth

import com.example.effective_testapp.data.bd.UserData

sealed class AuthState {
    object LoadingUser : AuthState()
    data class SuccessUser(val user: UserData) : AuthState()
    data class ErrorUser(val errorMessage: String) : AuthState()
    //data class Empty()
}
sealed class InputState {
    data class ErrorFirst(val errorMessage: String): InputState()
    data class ErrorLast(val errorMessage: String): InputState()
    data class ErrorNumber(val errorMessage: String): InputState()

    data class SuccessFirst(val firstName: String): InputState()
    data class SuccessLast(val firstName: String): InputState()
    data class SuccessNumber(val firstName: String): InputState()

}