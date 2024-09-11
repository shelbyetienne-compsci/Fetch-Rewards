package com.example.fetchrewards

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.gson.GsonConverterFactory

class HiringViewModel: ViewModel() {
    var hiringData = mutableStateOf<List<HiringItem>>(emptyList())
        private set

    init {
        getHiringData()
    }

    private fun getHiringData() {
        val retroFit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("")
            .build()
            .create(ApiInterface::class.java)

        viewModelScope.launch {
            try {
                val response = retroFit.getData().await()
                hiringData.value = response.filter { it.name.isNotEmpty() }
            } catch (e:Exception) {
                e.printStackTrace()
                hiringData.value = emptyList()
            }
        }
    }
}