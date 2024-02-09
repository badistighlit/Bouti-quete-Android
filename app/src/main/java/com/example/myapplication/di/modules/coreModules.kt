package com.example.myapplication.di.modules


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
    single { LocalisationMapper() }
    viewModel { ListeMagasinProcheViewModel(get(), get()) }
    viewModel { ListeProduitViewModel() }
    viewModel {ListeMagasinViewModel()}
    single { ApiCalcule(get()) }
    single { LocalisationRepository(get()) }
    single { RetrofitClient.create() }


    single { DatabaseRepository(get()) }

}



val appModules = listOf(coreModules, databaseModule)
