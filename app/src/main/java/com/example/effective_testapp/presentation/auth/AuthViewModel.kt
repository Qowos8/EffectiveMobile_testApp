package com.example.effective_testapp.presentation.auth

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effective_testapp.data.bd.UserData
import com.example.effective_testapp.data.bd.UserDataBase
import kotlinx.coroutines.launch

class AuthViewModel: ViewModel() {
    private lateinit var userDB: UserDataBase
    var firstName = ""
    var lastName = ""
    var number = ""

    fun initDatabase(context: Context) {
        userDB = UserDataBase.newInstance(context.applicationContext)
    }

    fun closeDatabase() {
        if (::userDB.isInitialized) {
            userDB.close()
        }
    }
    fun inputNameCheck(input: String): Boolean {
        val regex = "^[а-яА-ЯёЁ]+$".toRegex()
        return regex.matches(input)
    }
    fun inputNumberCheck(input: String): Boolean {
        val regex = "^[0-9]+$".toRegex()
        return regex.matches(input)
    }
    fun getFirstName(first: String){
        firstName = first
    }
    fun getLastName(last: String){
        lastName = last
    }
    fun getNumber(num: String){
        number = num
    }

    fun insertToDB(context: Context){
        viewModelScope.launch {
            initDatabase(context)
            userDB.DaoUser().insertData(UserData(firstName = firstName, lastName =  lastName, number =  number))
            Log.d("UserDb", "${UserData(firstName = firstName, lastName =  lastName, number =  number)}")
            closeDatabase()
        }
    }

}