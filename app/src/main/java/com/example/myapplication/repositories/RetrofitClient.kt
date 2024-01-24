package com.example.myapplication.repositories

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.myapplication.network.LocalisationApi
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory

class RetrofitClient {
    companion object {
        private const val BASE_URL = "https://maps.googleapis.com/maps/api/"

        fun create(): LocalisationApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) // Ajoutez l'adaptateur RxJava 3
                .build()

            return retrofit.create(LocalisationApi::class.java)
        }
    }
}
