package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.view.Extensions.setupBottomNavigation
import com.example.myapplication.view.ListeMagasinProche
import com.example.myapplication.view.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class RenseigneAdresseActivity : AppCompatActivity() {
    lateinit var bottomNavigationView: BottomNavigationView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_renseigne_adresse)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setupBottomNavigation(this)

        val nomEditText: EditText = findViewById(R.id.nom)
        val prenomEditText: EditText = findViewById(R.id.prenom)
        val rueEditText: EditText = findViewById(R.id.rue)
        val villeEditText: EditText = findViewById(R.id.ville)
        val postalCodeEditText: EditText = findViewById(R.id.postalCode)
        val validerButton: Button = findViewById(R.id.valider)
        val backReturn: Button = findViewById(R.id.backReturn)
        val getLocalisation: Button = findViewById(R.id.getLocalisation)

        validerButton.setOnClickListener {
            val nom = nomEditText.text.toString()
            val prenom = prenomEditText.text.toString()
            val rue = rueEditText.text.toString()
            val ville = villeEditText.text.toString()
            val postalCode = postalCodeEditText.text.toString()

            // Badis récupe les données ici, modifie le nom de la classe
            val intent = Intent(this, ListeMagasinProche::class.java).apply {
                putExtra("NOM", nom)
                putExtra("PRENOM", prenom)
                putExtra("RUE", rue)
                putExtra("VILLE", ville)
                putExtra("CODE POSTAL", postalCode)
            }
            startActivity(intent)
        }

        getLocalisation.setOnClickListener {

        }

        backReturn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent);
            finish();
        })

    }
}
