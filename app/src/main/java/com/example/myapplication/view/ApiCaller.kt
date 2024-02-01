import android.util.Log
import com.example.myapplication.network.LocalisationApi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.*



class ApiCaller {

    private val TAG = "ApiCaller"

    fun performApiCall(adresse: String, callback: (String, String) -> Unit) {
        // Initialiser Retrofit pour faire l'appel à l'API
        try {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/maps/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()

            val apiService = retrofit.create(LocalisationApi::class.java)

            // Faire l'appel à l'API de manière asynchrone avec RxJava
            apiService.getLatLong(adresse, "AIzaSyDBlWUao8U9kxDpb3_9OT9BlNeWsKMJ46A")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        Log.d(TAG, "Réponse de l'API: $response")
                        val lat = response.results[0].geometry.location.latitude.toString()
                        val lng = response.results[0].geometry.location.longitude.toString()
                        callback(lat, lng) // Utiliser le callback pour retourner la latitude et la longitude
                    },
                    { error ->
                        Log.e(TAG, "Erreur lors de la demande à l'API: ${error.message}")
                        error.printStackTrace() // Imprimer la pile d'appels
                    }
                )
        } catch (e: Exception) {
            Log.e(TAG, "Erreur inattendue: ${e.message}")
            e.printStackTrace() // Imprimer la pile d'appels
        }
    }

    fun getDistance(latInit: Double, longInit: Double, latFinal: Double, longFinal: Double): Double {
        val R  = 6371  // Rayon moyen de la Terre en kilomètres
        val (rLat1, rLon1, rLat2, rLon2) = listOf(latInit, longInit, latFinal, longFinal).map { Math.toRadians(it) }

        // Calcul des différences de coordonnées
        val dLat = rLat2 - rLat1
        val dLon = rLon2 - rLon1

        // Formule de trigonométrie sphérique
        val a = sin(dLat / 2).pow(2) + cos(rLat1) * cos(rLat2) * sin(dLon / 2).pow(2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))

        var distance = R * c

        return (round(distance * 100) / 100)
    }

}

