package br.com.pucpr.homieworks.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("http://192.168.1.11:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

