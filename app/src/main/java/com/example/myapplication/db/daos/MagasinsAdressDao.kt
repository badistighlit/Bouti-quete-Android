package com.example.myapplication.db.daos

import androidx.room.Dao
import androidx.room.Query
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


@Dao
interface MagasinsAdressDao {
    @Query("SELECT * FROM magasins_entity INNER JOIN adress_entity ON magasins_entity.magasinId = adress_entity.adressId")
    fun getAll(): List<MagasinAdresse>


}