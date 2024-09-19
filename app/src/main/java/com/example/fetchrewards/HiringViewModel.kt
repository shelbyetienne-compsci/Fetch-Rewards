package com.example.fetchrewards

import android.util.Log
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.await


class HiringViewModel: ViewModel() {
    val userSortBy = mutableStateOf(SortBy.Name)
    var hiringData = derivedStateOf<Map<Int ,List<HiringItem>>>{
        runBlocking {
            val response = retroFit.getData().await()
            response.filter { !it.name.isNullOrEmpty() }
                .groupBy { it.listId }
                .toSortedMap().mapValues {
                    when(userSortBy.value){
                        SortBy.Name -> it.value.sortedBy { i -> i.name }
                        SortBy.Price -> it.value.sortedBy { i -> i.price }
                        SortBy.Rating -> it.value.sortedBy { i -> i.rating }
                    }
                }
        }
    }
        private set

    enum class SortBy{
        Name,
        Price,
        Rating
    }

}