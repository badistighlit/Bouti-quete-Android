package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class RenseigneAdresseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_renseigne_adresse)

        val nomEditText: EditText = findViewById(R.id.nom)
        val prenomEditText: EditText = findViewById(R.id.prenom)
        val adresseEditText: EditText = findViewById(R.id.adresse)
        val validerButton: Button = findViewById(R.id.valider)

        validerButton.setOnClickListener {
            val nom = nomEditText.text.toString()
            val prenom = prenomEditText.text.toString()
            val adresse = adresseEditText.text.toString()

            // Badis récupe les données ici, modifie le nom de la classe
            val intent = Intent(this, ListeMagasinProche::class.java).apply {
                putExtra("NOM", nom)
                putExtra("PRENOM", prenom)
                putExtra("ADRESSE", adresse)
            }
            startActivity(intent)
        }

    }
}
