package com.example.fetchrewards

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://fetch-hiring.s3.amazonaws.com/"

class HiringViewModel: ViewModel() {
    var hiringData = mutableStateOf<List<HiringItem>>(emptyList())
        private set

    init {
        getHiringData()
    }

    private fun getHiringData() {
        val retroFit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        viewModelScope.launch {
            try {
                val response = retroFit.getData().await()
                hiringData.value = response.filter { !it.name.isNullOrEmpty() }
                Log.d("Api Call Made", hiringData.toString())
            } catch (e:Exception) {
                e.printStackTrace()
                hiringData.value = emptyList()
            }
        }
    }
}