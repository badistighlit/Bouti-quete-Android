package com.example.myapplication.view

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.db.AppDatabase
import com.example.myapplication.repositories.DatabaseRepository
import com.example.myapplication.view.Extentions.setupBottomNavigation
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

        if (isDarkModeEnabled()) {
            setTheme(R.style.Base_Theme_MyApplication_Dark);
        } else {
            setTheme(R.style.Base_Theme_MyApplication);
        }

        val databaseRepository: DatabaseRepository by inject()
        databaseRepository.build()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setupBottomNavigation(this)

    }
    private fun isDarkModeEnabled(): Boolean {
        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return currentNightMode == Configuration.UI_MODE_NIGHT_YES
    }

}
