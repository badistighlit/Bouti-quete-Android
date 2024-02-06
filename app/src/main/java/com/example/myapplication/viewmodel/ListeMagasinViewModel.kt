package com.example.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.magasin_model.Magasin
import com.example.myapplication.repositories.DatabaseRepository
import org.koin.java.KoinJavaComponent

class ListeMagasinViewModel : ViewModel()  {
    val databaseRepository: DatabaseRepository by KoinJavaComponent.inject(DatabaseRepository::class.java)

    init {
        databaseRepository.build()
    }

    val magasins =databaseRepository.getMagasins();

    fun getListeMagasins(): List<Magasin> {
        Log.v("popo","pipi")
        return this.magasins;
    }


}