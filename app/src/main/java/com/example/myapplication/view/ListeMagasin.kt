package com.example.myapplication
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.magasin_model.Adresse
import com.example.myapplication.model.magasin_model.Magasin
import com.example.myapplication.view.adapters.MagasinAdapter

class ListeMagasin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liste_magasin)
        val recyclerView: RecyclerView = findViewById(R.id.ListerecyclerView)



        //val adapter = MagasinAdapter(magasins)
        recyclerView.layoutManager = LinearLayoutManager(this)
        //recyclerView.adapter = adapter

    }
}


