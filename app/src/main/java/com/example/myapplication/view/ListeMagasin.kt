package com.example.myapplication.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.magasin_model.Magasin
import com.example.myapplication.view.ClickListeners.OnMagasinClickListener
import com.example.myapplication.view.Extentions.setupBottomNavigation
import com.example.myapplication.view.adapters.MagasinAdapter

import com.example.myapplication.viewmodel.ListeMagasinViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListeMagasin : AppCompatActivity() {
    private val listeMagasinviewModel: ListeMagasinViewModel by viewModel()
    lateinit var bottomNavigationView: BottomNavigationView;
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liste_magasin)
        val backReturn: Button = findViewById(R.id.backReturn)
        val searchItem: SearchView = findViewById(R.id.SearchItem)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setupBottomNavigation(this)

        val magasins = listeMagasinviewModel.getListeMagasins()
        val mappedmagasins =magasins.associateWith { magasin ->
            0.0
        }

        val adapter = MagasinAdapter(mappedmagasins, object : OnMagasinClickListener {
            override fun onMagasinClick(magasin: Magasin) {

                val intent = Intent(this@ListeMagasin, ListeProduit::class.java)

                startActivity(intent)
            }
        })
        val recyclerView: RecyclerView = findViewById(R.id.ListerecyclerView)

       recyclerView.layoutManager = LinearLayoutManager(this@ListeMagasin)
       recyclerView.adapter = adapter

        searchItem.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                adapter.filterList(newText)
                return true
            }
        })

        backReturn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        })

    }

}
