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

data class MagasinAdresse(
    val magasinId: Int,
    val magasins_name: String,
    val adressId: Int,
    val magasins_rue_name: String,
    val magasins_ville_name: String,
    val magasins_postalCode: String,
    val magasins_latitude: Double,
    val magasins_longitude: Double
)
