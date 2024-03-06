package com.example.myapplication.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "magasins_produit_entity")
data class MagasinProduitEntity (
    @PrimaryKey(autoGenerate = true) val magasinProduitId: Int,
    @ColumnInfo (name = "magasinId") val magasinId: Int,
    @ColumnInfo (name = "produitId") val produitId: Int
)