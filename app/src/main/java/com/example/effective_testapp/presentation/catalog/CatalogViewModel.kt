package com.example.effective_testapp.presentation.catalog

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effective_testapp.data.network.KtorClient
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch

class CatalogViewModel: ViewModel() {
    private val _responseState  = MutableLiveData<ResponseState>()
    val responseState: LiveData<ResponseState> get() = _responseState
    private val client = KtorClient()

    fun loadData() {
        viewModelScope.launch {
            try {
                _responseState.postValue(ResponseState.Loading)
                val response = client.getItems()
                Log.d("response", "$response")
                _responseState.postValue(ResponseState.Success(response))
            } catch (e: Exception) {
                _responseState.postValue(ResponseState.Error(e.message ?: "Unknown error"))
            }
        }
    }

    /*fun loadData() {
        viewModelScope.launch {
            val response = client.getItems()
                .subscribeOn(Schedulers.io())
                .flatMap { product ->
                    Single.fromCallable {
                        ResponseState.Success(product)
                    }.subscribeOn(Schedulers.io())
                }.observeOn(AndroidSchedulers.mainThread())
        }
    }*/
}