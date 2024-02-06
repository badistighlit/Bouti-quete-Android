package com.example.myapplication.model.magasin_model

data class Magasin(
    val id: Int,
    val nom: String,
    val adresse: Adresse
)

data class Adresse(
    val id: Int,
    val rue: String,
    val codePostal: String,
    val ville: String,
    val latitude: Double,
    val longitude : Double,

)