package com.example.myapplication.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "produit_entity")
data class ProduitEntity(
    @PrimaryKey(autoGenerate = true) val produitId: Int,
    @ColumnInfo(name = "produit_name") val produitName: String,
    @ColumnInfo(name = "produit_prix") val produitPrix: Double,
    @ColumnInfo(name = "produit_description") var produitDescription: String
)