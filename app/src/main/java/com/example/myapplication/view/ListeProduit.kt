package com.example.myapplication.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.magasin_model.Produit
import com.example.myapplication.view.ClickListeners.OnProduitClickListener
import com.example.myapplication.view.Extensions.setupBottomNavigation
import com.example.myapplication.view.adapters.ProduitAdapter
import com.example.myapplication.viewmodel.ListeProduitViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListeProduit : AppCompatActivity() {
    private val listeProduitViewModel: ListeProduitViewModel by viewModel()
    lateinit var bottomNavigationView: BottomNavigationView;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_produit)
        val backReturn: Button = findViewById(R.id.backReturn)
        val searchItem: SearchView = findViewById(R.id.SearchItem)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setupBottomNavigation(this)


        val adapter = ProduitAdapter(listeProduitViewModel.getListeProduits(), object :
            OnProduitClickListener {
            override fun OnProduitClick(produit: Produit) {
                // Action à effectuer lors du clic sur un magasin
                val intent = Intent(this@ListeProduit, DetailsProduit::class.java).apply {
                    putExtra("nom", produit.nom)
                    putExtra("prix",produit.prix.toString())
                    putExtra("details",produit.description)
                }

                startActivity(intent)
            }
        })
        val recyclerView: RecyclerView = findViewById(R.id.ListerecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this@ListeProduit)
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