package com.example.myapplication.view
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.example.myapplication.R
import com.example.myapplication.repositories.LocalisationRepository
import com.example.myapplication.repositories.RetrofitClient
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private val apiService = RetrofitClient.create()
    private val localisationRepository = LocalisationRepository(apiService)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val address = "Alger" // Remplacez par l'adresse que vous souhaitez rechercher

        localisationRepository.getLatLong(address)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                // Réponse réussie
                val latitude = response.results.firstOrNull()?.geometry?.location?.latitude
                val longitude = response.results.firstOrNull()?.geometry?.location?.longitude
                Log.d(TAG, "Latitude: $latitude, Longitude: $longitude")
            }, { error ->
                // Gérer les erreurs
                Log.e(TAG, "Erreur lors de l'appel à l'API : ${error.message}")
            })
    }
}
