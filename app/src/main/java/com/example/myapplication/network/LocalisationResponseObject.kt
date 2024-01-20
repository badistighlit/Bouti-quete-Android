package com.example.myapplication.network
import com.example.myapplication.model.coordinate_model.Coordinate
import com.google.gson.annotations.SerializedName

data class LocalisationResponseObject(
    @SerializedName("results")
    val results: List<LocalisationResult>,

    @SerializedName("status")
    val status: String
)

data class LocalisationResult(
    @SerializedName("geometry")
    val geometry: LocalisationGeometry
)

data class LocalisationGeometry(
    @SerializedName("location")
    val location: LocalisationData
)

data class LocalisationData(
    @SerializedName("lat")
    val latitude: Double,

    @SerializedName("lng")
    val longitude: Double
)
