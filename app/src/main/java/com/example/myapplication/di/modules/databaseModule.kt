package com.example.myapplication.di.modules

import androidx.room.Room
import com.example.myapplication.db.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java, "database-name"
        ).fallbackToDestructiveMigration().build()
    }

    single { get<AppDatabase>().MagasinsDao() }
    single { get<AppDatabase>().AdresseDao() }
    single { get<AppDatabase>().MagasinsAdressDao() }
}