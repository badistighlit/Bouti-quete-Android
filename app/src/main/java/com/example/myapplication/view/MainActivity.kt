package com.example.myapplication.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button
import com.example.myapplication.R
import com.example.myapplication.RenseigneAdresseActivity
import com.example.myapplication.viewmodel.ApiCalcule
import com.example.myapplication.viewmodel.LocalisationViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val localisationViewModel: LocalisationViewModel by viewModel()
    private val apiCalcule: ApiCalcule by lazy { ApiCalcule(localisationViewModel) }

    override fun onCreate(savedInstanceState: Bundle?) {
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
