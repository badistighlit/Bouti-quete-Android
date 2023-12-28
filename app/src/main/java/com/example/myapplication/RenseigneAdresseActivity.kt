package com.example.myapplication

import activity_renseigne_adresse
import android.R
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class RenseigneAdresseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Défini le contenu de la vue
        setContentView(R.layout.activity_renseigne_adresse)

        //trouve les vues par leurs ID
        val nomEditText: EditText = findViewById(R.id.nom)
        val prenomEditText: EditText = findViewById(R.id.prenom)
        val adresseEditText: EditText = findViewById(R.id.adresse)
        val validerButton: Button = findViewById(R.id.valider)

        validerButton.setOnClickListener {
            val nom = nomEditText.text.toString()
            val prenom = prenomEditText.text.toString()
            val adresse = adresseEditText.text.toString()

        }
    }
}
