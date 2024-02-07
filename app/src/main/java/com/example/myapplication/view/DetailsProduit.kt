package com.example.myapplication.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityDetailsProduitBinding

class DetailsProduit : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_produit)

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