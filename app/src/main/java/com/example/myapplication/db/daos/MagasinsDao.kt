package com.example.myapplication.db.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.myapplication.db.entities.*

@Dao
interface MagasinsDao {
    @Query("SELECT * FROM magasins_entity")
    fun getAllMagasins(): List<magasinsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAdress(adress: adressEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewMagasins(magasinsEntity: magasinsEntity)

    @Delete
    fun deleteMagasins(magasins: magasinsEntity, adress: adressEntity){
        val adressId = insertAdress(adress)
        magasins.adressId = adressId.toInt()
        insertNewMagasins(magasins)
    }

    @Transaction
    fun insertMagasinWithAdress(magasins: magasinsEntity, adress: adressEntity): Int {
        val adressId = insertAdress(adress)
        magasins.adressId = adressId.toInt()
        insertNewMagasins(magasins)

        return magasins.magasinId
    }
}