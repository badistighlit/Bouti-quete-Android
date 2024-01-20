package com.example.myapplication.repositories

import com.example.myapplication.network.LocalisationApi
import com.example.myapplication.network.LocalisationResponseObject
import io.reactivex.rxjava3.core.Flowable

class LocalisationRepository (private val ApiService:LocalisationApi){
    fun getLatLong(address: String): Flowable<LocalisationResponseObject> {
        return ApiService.getLatLong(address, "AIzaSyDBlWUao8U9kxDpb3_9OT9BlNeWsKMJ46A")
    }

}

