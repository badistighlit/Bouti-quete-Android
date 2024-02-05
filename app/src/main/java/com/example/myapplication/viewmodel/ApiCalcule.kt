package com.example.myapplication.viewmodel

import android.util.Log
import com.example.myapplication.model.magasin_model.Adresse
import com.example.myapplication.model.magasin_model.Magasin
import com.example.myapplication.network.observeOnce
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.round
import kotlin.math.sin
import kotlin.math.sqrt

class ApiCalcule(private val localisationViewModel: LocalisationViewModel) {

    val magasins = listOf(
        Magasin(1, "KIKLOUTOU", Adresse("01 Rue Emile Gilbert", "75012", "Paris",48.8460254,2.3732395)),
        Magasin(2, "magasin sniper", Adresse("3 rue félixe faure", "75015", "Paris",48.83919906616211,2.2848060131073)),
        Magasin(3, "mingo", Adresse("9 rue friant", "75014", "Paris",48.8261949,2.3247556)),
        Magasin(4, "hmimouch", Adresse("135 Avenue fontainebleau", "94270", "Le Kremlin-Bicêtre",48.81172561645508,2.3617711067199707)),
        Magasin(5, "DISBA", Adresse("Pl. du 4 Septembre", "13007", "Marseille",43.288631439208984,5.359172821044922)),
        Magasin(6, "La Maison des Outils", Adresse("22 Rue de la République", "13001", "Marseille",43.29780960083008,5.372910022735596)),
        Magasin(7, "SuperQuincaillerie", Adresse("18 Avenue de Verdun", "06500", "Menton",43.7761657,7.4944641)),
        Magasin(8, "BricoExpress", Adresse("12 Rue des Fabriques", "54100", "Nancy",48.6859096,6.1877869)),
        Magasin(9, "Quincaillerie Moderne", Adresse("5 Boulevard de la Liberté", "44800", "Saint-Herblain",47.2230224609375,-1.6039869785308838)),
        Magasin(10, "Outillorama", Adresse("26 Rue du Bassin du Commerce", "67000", "Strasbourg",48.58171844482422,7.789248466491699)),
        Magasin(11, "BricoFrance", Adresse("8 Avenue de la Gare", "33610 ", "Cestas",44.77375793457031,-0.701376736164093)),
        Magasin(13, "MegaBrico", Adresse("59 Rue Claude Farrère", "69003", "Lyon",45.74305555741711,4.893425951529609)),
        Magasin(14, "Quincaillerie Central", Adresse("1 Rue Georges Lefebvre", "59000", "Lille",50.6307878508149,3.075074021645734)),

        )

    suspend fun getMagasinsPlusProche(adresse: String) {
        val distancesTriees = getPlusProche(adresse)

        var compteur = 0
        for ((magasin, distance) in distancesTriees) {
            Log.d("test final", " Magasin: ${magasin.nom}, Distance: $distance km")
            compteur++
            if (compteur >= 3) {
                break
            }
        }
    }

    suspend fun getPlusProche(adresse: String): Map<Magasin, Double> {
        return suspendCoroutine { continuation ->
            localisationViewModel.localisationData.observeOnce { localisationData ->
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

            localisationViewModel.getLatLong(adresse)
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
