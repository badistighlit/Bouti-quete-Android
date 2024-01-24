package com.example.myapplication.view
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.myapplication.ListeMagasin
import com.example.myapplication.ListeMagasinProche
import com.example.myapplication.R
import com.example.myapplication.RenseigneAdresseActivity
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

        val address = "01 RUE EMILE GILBERT 75012"
/*
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
            })*/
            val renseignedAdresse: Button = findViewById(R.id.btnRenseigneAdresse);
            renseignedAdresse.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, RenseigneAdresseActivity::class.java)
            startActivity(intent); })

            val chercheMagasin: Button = findViewById(R.id.btnSearchStore);
            chercheMagasin.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, ListeMagasinProche::class.java)
            startActivity(intent); })

            val nosMagasin: Button = findViewById(R.id.btnListeMagasins);
           nosMagasin.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, ListeMagasin::class.java)
            startActivity(intent); })


            /*val chercheProduit: Button = findViewById(R.id.btnSearchProduct);
             chercheProduit.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, ListeMagasin::class.java)
            startActivity(intent); })*/


    }
}



