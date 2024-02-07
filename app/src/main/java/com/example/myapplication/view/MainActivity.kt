package com.example.myapplication.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.R
import com.example.myapplication.RenseigneAdresseActivity
import com.example.myapplication.db.AppDatabase
import com.example.myapplication.repositories.DatabaseRepository
import com.example.myapplication.view.Extensions.setupBottomNavigation
import com.example.myapplication.viewmodel.ApiCalcule
import com.example.myapplication.viewmodel.ListeMagasinProcheViewModel
import com.example.myapplication.viewmodel.ListeMagasinViewModel
import com.example.myapplication.viewmodel.ListeProduitViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val db: AppDatabase by inject()
    private val listeProduitViewModel : ListeProduitViewModel by viewModel()
    private val listeMagasinProcheViewModel: ListeMagasinProcheViewModel by viewModel()
    private val apiCalcule: ApiCalcule by lazy { ApiCalcule(listeMagasinProcheViewModel)
    }
    private val listeMagasinViewModel : ListeMagasinViewModel by viewModel()
    lateinit var bottomNavigationView: BottomNavigationView ;
    override fun onCreate(savedInstanceState: Bundle?) {
         val databaseRepository: DatabaseRepository by inject();
        databaseRepository.build();

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setupBottomNavigation(this)



        val searchProduct: Button = findViewById(R.id.btnSearchProduct)
        searchProduct.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, ListeProduit::class.java)
            startActivity(intent)
        })

        val renseignedAdresse: Button = findViewById(R.id.btnRenseigneAdresse)
        renseignedAdresse.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, RenseigneAdresseActivity::class.java)
            startActivity(intent)
        })

        val nosMagasin: Button = findViewById(R.id.btnListeMagasins)
        nosMagasin.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, ListeMagasin::class.java)
            startActivity(intent)
        })

    }

}
