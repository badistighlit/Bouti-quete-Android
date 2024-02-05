package com.example.myapplication.di.modules

import com.example.myapplication.network.LocalisationApi
import com.example.myapplication.network.LocalisationMapper
import com.example.myapplication.repositories.LocalisationRepository
import com.example.myapplication.repositories.RetrofitClient
import com.example.myapplication.viewmodel.ApiCalcule
import com.example.myapplication.viewmodel.LocalisationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val coreModules = module {
    single { LocalisationMapper() } // Ajoutez cette ligne pour définir le LocalisationMapper comme un bean Koin
    viewModel { LocalisationViewModel(get(), get()) }
    single { ApiCalcule(get()) }
    single { LocalisationRepository(get()) }
    single { RetrofitClient.create() }
}

fun createLocalisationApi(): LocalisationApi {
    return RetrofitClient.create()
}