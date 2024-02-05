package com.example.myapplication.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "adress_entity")
data class adressEntity(
    @PrimaryKey(autoGenerate = true) val adressId: Int,
    @ColumnInfo(name = "magasins_rue_name") val magasinsRueName: String,
    @ColumnInfo(name = "magasins_ville_name") val magasinsvilleId: String,
    @ColumnInfo(name = "magasins_postalCode") val magasinsPostalCode: String
)

