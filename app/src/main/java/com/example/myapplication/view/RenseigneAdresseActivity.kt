package com.example.myapplication.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.view.Extentions.setupBottomNavigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import RenseigneAdresseViewModel
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.viewmodel.ListeMagasinProcheViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class RenseigneAdresseActivity : AppCompatActivity() {

    lateinit var bottomNavigationView: BottomNavigationView
    private val listeMagasinProcheViewModel: ListeMagasinProcheViewModel by viewModel()
    private val RenseigneAdresseViewModel: RenseigneAdresseViewModel by lazy { RenseigneAdresseViewModel(listeMagasinProcheViewModel, this) }
    private lateinit var rueEditText: EditText
    private lateinit var villeEditText: EditText
    private lateinit var postalCodeEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_renseigne_adresse)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setupBottomNavigation(this)

        val nomEditText: EditText = findViewById(R.id.nom)
        val prenomEditText: EditText = findViewById(R.id.prenom)
        val validerButton: Button = findViewById(R.id.valider)
        val backReturn: Button = findViewById(R.id.backReturn)
        val getLocalisation: Button = findViewById(R.id.getLocalisation)

        var fusedLocationProviderClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        rueEditText = findViewById(R.id.rue)
        villeEditText = findViewById(R.id.ville)
        postalCodeEditText = findViewById(R.id.postalCode)

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
            lifecycleScope.launch {
                val position = RenseigneAdresseViewModel.getLocationPosition(fusedLocationProviderClient, this)
                if (position != null) {
                    rueEditText.setText(position.rue)
                    villeEditText.setText(position.ville)
                    postalCodeEditText.setText(position.codePostal)
                }
            }
        }


        backReturn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent);
            finish()
        })

    }
}
