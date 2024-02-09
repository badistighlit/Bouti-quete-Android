package com.example.myapplication.repositories

import android.util.Log
import com.example.myapplication.network.LocalisationApi
import com.example.myapplication.network.LocalisationResponseObject
import com.example.myapplication.network.LocalisationResponseObjectAdress
import io.reactivex.rxjava3.core.Flowable

class LocalisationRepository (private val ApiService:LocalisationApi){

    private val apiService = RetrofitClient.create()

    fun getLatLong(address: String): Flowable<LocalisationResponseObject> {
        return apiService.getLatLong(address, "AIzaSyDBlWUao8U9kxDpb3_9OT9BlNeWsKMJ46A")
    }

    fun getAddress(coordinate: Pair<Double, Double>): Flowable<LocalisationResponseObjectAdress> {
        return apiService.getAdress("${coordinate.first},${coordinate.second}", "ROOFTOP", "street_address", "AIzaSyDBlWUao8U9kxDpb3_9OT9BlNeWsKMJ46A")
            .doOnNext { response ->
                // Log the response
                Log.d("LocalisationRepository", "Address response: $response")
            }
            .doOnError { error ->
                // Log any error that occurred during the request
                Log.e("LocalisationRepository", "Error fetching address: ${error.message}")
            }
    }


}

