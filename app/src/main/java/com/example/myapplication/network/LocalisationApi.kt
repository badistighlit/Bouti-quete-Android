package com.example.myapplication.network

import io.reactivex.rxjava3.core.Flowable
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface LocalisationApi {

    @GET("geocode/json")
    fun getLatLong(
        @Query("address") address: String,
        @Query("key") apiKey: String
    ): Flowable<LocalisationResponseObject>

    @GET("geocode/json")
    fun getAdress(
        @Query("latlng") coordinateLat: String,
        @Query("location_type") locationType: String,
        @Query("result_type") resultType: String,
        @Query("key") apiKey: String
    ): Flowable<LocalisationResponseObjectAdress>

}