package com.example.myapplication.di.modules

import com.example.myapplication.network.LocalisationMapper
import com.example.myapplication.repositories.LocalisationRepository
import com.example.myapplication.viewmodel.LocalisationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

internal val coreModules: Module = module {
    single { LocalisationRepository(get()) }
    single { LocalisationMapper() }
    viewModel { LocalisationViewModel(get(), get()) }
}