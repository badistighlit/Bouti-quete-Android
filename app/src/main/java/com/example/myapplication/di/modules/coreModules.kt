package com.example.myapplication.di.modules

import androidx.lifecycle.ViewModel
import com.example.myapplication.network.LocalisationApi
import com.example.myapplication.network.LocalisationMapper
import com.example.myapplication.repositories.DatabaseRepository
import com.example.myapplication.repositories.LocalisationRepository
import com.example.myapplication.repositories.RetrofitClient
import com.example.myapplication.viewmodel.ApiCalcule
import com.example.myapplication.viewmodel.ListeMagasinProcheViewModel
import com.example.myapplication.viewmodel.ListeMagasinViewModel
import com.example.myapplication.viewmodel.ListeProduitViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val coreModules = module {
    single { LocalisationMapper() } // Ajoutez cette ligne pour définir le LocalisationMapper comme un bean Koin
    viewModel { ListeMagasinProcheViewModel(get(), get()) }
    viewModel { ListeProduitViewModel() }
    viewModel {ListeMagasinViewModel()}
    single { ApiCalcule(get()) }
    single { LocalisationRepository(get()) }
    single { RetrofitClient.create() }


    single { DatabaseRepository(get()) }

}

fun createLocalisationApi(): LocalisationApi {
    return RetrofitClient.create()
}

val appModules = listOf(coreModules, databaseModule)
