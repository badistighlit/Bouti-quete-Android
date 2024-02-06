package com.example.myapplication.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.db.daos.AdresseDao
import com.example.myapplication.db.daos.MagasinsAdressDao
import com.example.myapplication.db.daos.MagasinsDao
import com.example.myapplication.db.entities.*

@Database(
    entities = [magasinsEntity::class, adressEntity::class], // Ajoutez vos entités ici
    version = 2
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun MagasinsDao(): MagasinsDao

    abstract fun AdresseDao(): AdresseDao

    abstract fun MagasinsAdressDao(): MagasinsAdressDao


}