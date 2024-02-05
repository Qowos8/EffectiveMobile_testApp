package com.example.effective_testapp

import com.example.effective_testapp.data.bd.UserDataBase
import android.app.Application

class Application: Application() {
    val dataBase by lazy { UserDataBase.newInstance(this) }
}