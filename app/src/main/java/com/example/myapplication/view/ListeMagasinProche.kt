package com.example.myapplication.view

import ApiCaller
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.view.MainActivity
import com.example.myapplication.view.adapters.MagasinAdapter
import com.example.myapplication.viewmodel.ApiCalcule
import com.example.myapplication.viewmodel.LocalisationViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

val apiCaller = ApiCaller()

class ListeMagasinProche : AppCompatActivity() {

    private val localisationViewModel: LocalisationViewModel by viewModel()
    private val apiCalcule: ApiCalcule by lazy { ApiCalcule(localisationViewModel) }
      @SuppressLint("SuspiciousIndentation")
      override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liste_magasin_proche)
        val backReturn: Button = findViewById(R.id.backReturn)

        val nom = intent.getStringExtra("NOM")
        val prenom = intent.getStringExtra("PRENOM")
        val rue = intent.getStringExtra("RUE")
        val ville = intent.getStringExtra("VILLE")
        val postalCode = intent.getStringExtra("CODE POSTAL")
        val adresse = rue + ville + postalCode;

        val textView = findViewById<TextView>(R.id.Salutation)
        val textView2 = findViewById<TextView>(R.id.textView)
        textView.text = "BONJOUR " + nom + " " + prenom + " votre adresse est la suivante : \n" + adresse +
                "\nVoici la liste des magasins les plus proche de chez vous";

          lifecycleScope.launch {
              Log.d("ListeMagasinProche", "Avant appel à apiCalcule.getPlusProche")
              val magasins = apiCalcule.getPlusProche(adresse)
              Log.d("ListeMagasinProche", "Après appel à apiCalcule.getPlusProche")
              Log.d("test zboub", " $magasins")
              val adapter = MagasinAdapter(magasins)
              val recyclerView: RecyclerView = findViewById(R.id.ListerecyclerView)
              recyclerView.layoutManager = LinearLayoutManager(this@ListeMagasinProche)
              recyclerView.adapter = adapter
          }



        */


        backReturn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        })
    }


}