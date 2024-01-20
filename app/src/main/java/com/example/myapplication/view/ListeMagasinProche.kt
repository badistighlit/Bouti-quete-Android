package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.magasin_model.Magasin
import com.example.myapplication.view.adapters.MagasinAdapter

class ListeMagasinProche : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liste_magasin_proche)
        val nom = intent.getStringExtra("NOM")
        val prenom = intent.getStringExtra("PRENOM")
        val adresse = intent.getStringExtra("ADRESSE")
        val textView = findViewById<TextView>(R.id.Salutation)
        textView.text = "BONJOUR"+nom+prenom+"votre adresse est la suivante : "+ adresse+
                "/n voici la liste des magasins les plus proche de chez vous"
        val magasins = listOf(
            Magasin(1, "KIKLOUTOU", "01 Rue Emile Gilbert, 75012 Paris"),
            Magasin(2, "magasin sniper", "3 rue félixe faure, 75015 Paris"),
            Magasin(3, "mingo", "9 rue friant , 75014 Paris"),
            Magasin(4,"hmimouch","135 Avenue fontainebleau, 94270 Le Kremlin-Bicêtre"),
            Magasin(5,"DISBA"," Pl. du 4 Septembre, 13007 Marseille"),


        )

        val adapter = MagasinAdapter(magasins)
        val recyclerView: RecyclerView = findViewById(R.id.ListerecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}