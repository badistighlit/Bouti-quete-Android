package com.example.myapplication.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.magasin_model.Produit
import com.example.myapplication.view.adapters.ProduitAdapter
import com.example.myapplication.viewmodel.ListeMagasinProcheViewModel
import com.example.myapplication.viewmodel.ListeProduitViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListeProduit : AppCompatActivity() {
    private val listeProduitViewModel: ListeProduitViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_produit)
        val backReturn: Button = findViewById(R.id.backReturn)


        val adapter = ProduitAdapter(listeProduitViewModel.getListeProduits())
        val recyclerView: RecyclerView = findViewById(R.id.ListerecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this@ListeProduit)
        recyclerView.adapter = adapter

        backReturn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        })


    }


}