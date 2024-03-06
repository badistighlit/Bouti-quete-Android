package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myapplication.model.magasin_model.Produit
import com.example.myapplication.repositories.DatabaseRepository
import org.koin.java.KoinJavaComponent

class ListeProduitViewModel() : ViewModel() {
    val databaseRepository: DatabaseRepository by KoinJavaComponent.inject(DatabaseRepository::class.java)

    init {
        databaseRepository.buildIfNeeded()
    }
    fun getListProduitsForMagasin(id: Int): List<Produit> {
        return databaseRepository.getProduitsByMagasinId(id)
    }

    fun getListeProduits(): List<Produit> {
        return databaseRepository.getAllProducts()
    }

}