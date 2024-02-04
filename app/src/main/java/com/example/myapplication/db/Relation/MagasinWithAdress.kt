package com.example.myapplication.db.Relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.myapplication.db.entities.adressEntity
import com.example.myapplication.db.entities.magasinsEntity

data class MagasinWithAdress(
    @Embedded val magasins: magasinsEntity,
    @Relation(
        parentColumn = "adress_id",
        entityColumn = "uid"
    )
    val adress: adressEntity

)


