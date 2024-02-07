package com.example.myapplication.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.myapplication.R
import com.example.myapplication.db.AppDatabase
import com.example.myapplication.db.daos.MagasinAdresse
import com.example.myapplication.db.entities.*
import com.example.myapplication.model.magasin_model.Adresse
import com.example.myapplication.model.magasin_model.Magasin
import com.example.myapplication.view.adapters.MagasinAdapter
import com.example.myapplication.view.adapters.OnMagasinClickListener
import com.example.myapplication.view.adapters.ProduitAdapter
import com.example.myapplication.viewmodel.ListeMagasinProcheViewModel
import com.example.myapplication.viewmodel.ListeMagasinViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListeMagasin : AppCompatActivity() {
    private val listeMagasinviewModel: ListeMagasinViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liste_magasin)
        val searchItem: SearchView = findViewById(R.id.SearchItem)


        val magasins = listeMagasinviewModel.getListeMagasins();
        val mappedmagasins =magasins.associateWith { magasin ->
            0.0
        }

        val adapter = MagasinAdapter(mappedmagasins, object : OnMagasinClickListener {
            override fun onMagasinClick(magasin: Magasin) {
                // Action à effectuer lors du clic sur un magasin
                val intent = Intent(this@ListeMagasin, ListeProduit::class.java)
                // Ajoutez des données supplémentaires à l'intent si nécessaire
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



    }





}


