package com.example.myapplication.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.view.adapters.MagasinAdapter
import com.example.myapplication.viewmodel.ListeMagasinViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListeMagasin : AppCompatActivity() {
    private val listeMagasinviewModel: ListeMagasinViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liste_magasin)
        val searchItem: SearchView = findViewById(R.id.SearchItem)

        val magasins = listeMagasinviewModel.getListeMagasins()
        val mappedmagasins =magasins.associateWith { magasin ->
            0.0
        }

       val adapter = MagasinAdapter(mappedmagasins)
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
