package com.example.myapplication.db.daos

import androidx.room.Dao
import androidx.room.Query
import com.example.myapplication.model.magasin_model.MagasinAdresse

@Dao
interface MagasinsAdressDao {
    @Query("SELECT * FROM magasins_entity INNER JOIN adress_entity ON magasins_entity.magasinId = adress_entity.adressId")
    fun getAll(): List<MagasinAdresse>


}