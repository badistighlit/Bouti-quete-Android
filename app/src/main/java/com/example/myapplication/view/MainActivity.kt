package com.example.myapplication.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button
import androidx.lifecycle.Observer
import com.example.myapplication.R
import com.example.myapplication.RenseigneAdresseActivity
import com.example.myapplication.db.AppDatabase
import com.example.myapplication.repositories.DatabaseRepository
import com.example.myapplication.viewmodel.ApiCalcule
import com.example.myapplication.viewmodel.LocalisationViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val db: AppDatabase by inject()
    private val localisationViewModel: LocalisationViewModel by viewModel()
    private val apiCalcule: ApiCalcule by lazy { ApiCalcule(localisationViewModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
         val databaseRepository: DatabaseRepository by inject();
        databaseRepository.build();
        val adresse = "Lyon"
        val plusProcheLiveData = localisationViewModel.getPlusProche(adresse)

        // Observe the LiveData in your activity
        plusProcheLiveData.observe(this, Observer { result ->
            // Handle the result here
            Log.v("pipo", "$result")
        })





        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val renseignedAdresse: Button = findViewById(R.id.btnRenseigneAdresse)
        renseignedAdresse.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, RenseigneAdresseActivity::class.java)
            startActivity(intent)
        })

        val chercheMagasin: Button = findViewById(R.id.btnSearchStore)
        chercheMagasin.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, ListeMagasinProche::class.java)
            startActivity(intent)
        })

        val nosMagasin: Button = findViewById(R.id.btnListeMagasins)
        nosMagasin.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, ListeMagasin::class.java)
            startActivity(intent)
        })
    }
}
