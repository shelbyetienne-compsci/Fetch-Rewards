package com.example.fetchrewards

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://fetch-hiring.s3.amazonaws.com/"

val retroFit: ApiInterface = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()
    .create(ApiInterface::class.java)

interface ApiInterface {
    @GET(value = "hiring.json")
    fun getData(): Call<List<HiringItem>>
}