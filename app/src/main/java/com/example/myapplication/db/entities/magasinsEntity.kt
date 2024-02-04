package com.example.myapplication.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "magasins_entity")
data class magasinsEntity(
    @PrimaryKey(autoGenerate = true) val magasinId: Int,
    @ColumnInfo(name = "magasins_name") val magasinsName: String,
    @ColumnInfo(name = "adress_id") var adressId: Int
)

