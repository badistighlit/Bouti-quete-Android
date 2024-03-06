package com.example.myapplication.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.db.entities.ProduitEntity

@Dao
interface ProduitDao {
    @Query("SELECT * FROM produit_entity")
    fun getAllproduits(): List<ProduitEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduit(produit: ProduitEntity): Long

}