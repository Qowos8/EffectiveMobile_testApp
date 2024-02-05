package com.example.effective_testapp.presentation.profile

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effective_testapp.data.bd.UserDao
import com.example.effective_testapp.data.bd.UserData
import com.example.effective_testapp.data.bd.UserDataBase
import kotlinx.coroutines.launch

class ProfileViewModel: ViewModel() {
    private lateinit var userDB: UserDataBase
    private val _bdState = MutableLiveData<DataState>()
    val bdState: LiveData<DataState> get() = _bdState
    fun initDatabase(context: Context) {
        userDB = UserDataBase.newInstance(context.applicationContext)
    }

    fun closeDatabase() {
        if (::userDB.isInitialized) {
            userDB.close()
        }
    }
    fun getUserData(context: Context) {
        viewModelScope.launch {
            initDatabase(context)
            _bdState.postValue(DataState.SuccessUser(userDB.DaoUser().getData().last()))
            closeDatabase()
        }
    }
}