package com.example.maket

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


public interface ApiInterface {

    @GET("/workers")
    fun getCategoryDetails(): Call<List<Worker>>

    companion object Factory {
        val BASE_URL = "http://bosses.sharapov.uz:8800"
        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}