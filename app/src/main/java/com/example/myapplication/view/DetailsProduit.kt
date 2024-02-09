package com.example.myapplication.view


import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.view.Extentions.setupBottomNavigation
import com.google.android.material.bottomnavigation.BottomNavigationView

class DetailsProduit : AppCompatActivity() {

    lateinit var bottomNavigationView: BottomNavigationView;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_produit)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setupBottomNavigation(this)

        val nom = intent.getStringExtra("nom")
        val prix = intent.getStringExtra("prix")
        val details = intent.getStringExtra("details")

        val nomView: TextView = findViewById(R.id.nom)
        val prixView: TextView = findViewById(R.id.prix)
        val detailsView: TextView = findViewById(R.id.Description)

      nomView.text=nom;
        prixView.text=prix+" €";
        detailsView.text=details




    }


}