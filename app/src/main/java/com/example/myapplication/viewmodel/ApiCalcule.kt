package com.example.myapplication.viewmodel

import com.example.myapplication.model.magasin_model.Magasin
import com.example.myapplication.network.observeOnce
import com.example.myapplication.repositories.DatabaseRepository
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.round
import kotlin.math.sin
import kotlin.math.sqrt
import org.koin.java.KoinJavaComponent.inject;

class ApiCalcule(private val listeMagasinProcheViewModel: ListeMagasinProcheViewModel) {
 //   val databaseRepository: DatabaseRepository
 val databaseRepository: DatabaseRepository by inject(DatabaseRepository::class.java)

    init {
        databaseRepository.build()
    }

val magasins =databaseRepository.getMagasins();

    suspend fun getPlusProche(adresse: String): Map<Magasin, Double> {
        return suspendCoroutine { continuation ->
            listeMagasinProcheViewModel.localisationData.observeOnce { localisationData ->
                localisationData?.let {
                    val latitude = it.latitude
                    val longitude = it.longitude

                    val distances = magasins.associateWith { magasin ->
                        getDistanceCordinate(latitude, longitude, magasin.adresse.latitude, magasin.adresse.longitude)
                    }

                    val distancesTriees = distances.toList().sortedBy { it.second }.toMap()
                    continuation.resume(distancesTriees)
                }
            }

            listeMagasinProcheViewModel.getLatLong(adresse)
        }
    }


    private fun getDistanceCordinate(latInit: Double, longInit: Double, latFinal: Double, longFinal: Double): Double {
        val R = 6371  // Rayon moyen de la Terre en kilomètres
        val (rLat1, rLon1, rLat2, rLon2) = listOf(latInit, longInit, latFinal, longFinal).map { Math.toRadians(it) }

        val dLat = rLat2 - rLat1
        val dLon = rLon2 - rLon1

        val a = sin(dLat / 2).pow(2) + cos(rLat1) * cos(rLat2) * sin(dLon / 2).pow(2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))

        var distance = R * c

        return (round(distance * 100) / 100)
    }
}
