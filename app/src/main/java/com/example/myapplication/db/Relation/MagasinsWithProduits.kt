package com.example.myapplication.db.Relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.myapplication.db.entities.ProduitEntity
import com.example.myapplication.db.entities.magasinsEntity



data class MagasinsWithProduits(
    @Embedded val magasin: magasinsEntity,
    @Relation(
        parentColumn = "magasinId",
        entityColumn = "magasinId"
    )
    val produits: MutableList<ProduitEntity>
)