package com.example.fetchrewards

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET(value = "hiring.json")
    fun getData(): Call<List<HiringItem>>
}