package com.example.myapplication.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.db.entities.MagasinProduitEntity
import com.example.myapplication.db.entities.ProduitEntity
import com.example.myapplication.db.entities.magasinsEntity
@Dao
interface MagasinProduitDao {
    @Query("SELECT * FROM magasins_produit_entity INNER JOIN magasins_entity, produit_entity " +
            "Where magasins_entity.magasinId = magasins_produit_entity.magasinId " +
            "And produit_entity.produitId = magasins_produit_entity.produitId")
    fun getAll(): List<MagasinProduitEntity>

    @Query("SELECT magasins_entity.* FROM magasins_entity " +
            "INNER JOIN magasins_produit_entity ON magasins_entity.magasinId = magasins_produit_entity.magasinId " +
            "WHERE magasins_produit_entity.produitId = :produitId")
    fun getMagasinsForProduct(produitId: Long): List<magasinsEntity>

    @Query("SELECT produit_entity.* FROM produit_entity " +
            "INNER JOIN magasins_produit_entity ON produit_entity.produitId = magasins_produit_entity.produitId " +
            "WHERE magasins_produit_entity.magasinId = :magasinId")
    fun getProductsForMagasin(magasinId: Long): List<ProduitEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMagasinProduit(magasinProduitEntity: MagasinProduitEntity)
}