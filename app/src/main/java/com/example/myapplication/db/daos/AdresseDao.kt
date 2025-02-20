package com.example.myapplication.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.db.entities.*

@Dao
interface AdresseDao {
    @Query("SELECT * FROM adress_entity")
    fun getAllAdress(): List<adressEntity>

    @Insert
    fun insertAdress(adress: adressEntity): Long


}