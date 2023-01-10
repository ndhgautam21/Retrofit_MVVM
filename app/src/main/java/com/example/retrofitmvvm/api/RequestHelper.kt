package com.example.retrofitmvvm.api

import okhttp3.HttpUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL

object RequestHelper {

    private const val BASE_URL = "http://10.1.3.25:8080/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    fun getValue() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.29.251:8080/").build()
    }
}