package com.example.myapplication.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.db.daos.AdresseDao
import com.example.myapplication.db.daos.MagasinProduitDao
import com.example.myapplication.db.daos.MagasinsAdressDao
import com.example.myapplication.db.daos.MagasinsDao
import com.example.myapplication.db.daos.ProduitDao
import com.example.myapplication.db.entities.*

@Database(
    entities = [magasinsEntity::class, adressEntity::class, ProduitEntity::class, MagasinProduitEntity::class],
    version = 4
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun MagasinsDao(): MagasinsDao

    abstract fun AdresseDao(): AdresseDao

    abstract fun MagasinsAdressDao(): MagasinsAdressDao

    abstract fun ProduitDao(): ProduitDao

    abstract fun MagasinProduitDao(): MagasinProduitDao

}