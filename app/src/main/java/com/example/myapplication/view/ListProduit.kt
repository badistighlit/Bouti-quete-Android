package com.example.myapplication.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.magasin_model.Magasin
import com.example.myapplication.model.magasin_model.Produit
import com.example.myapplication.view.adapters.ProduitAdapter

class ListProduit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_produit)
        val backReturn: Button = findViewById(R.id.backReturn)


        val adapter = ProduitAdapter(listMagasin())
        val recyclerView: RecyclerView = findViewById(R.id.ListerecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this@ListProduit)
        recyclerView.adapter = adapter

        backReturn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        })


    }
    fun listMagasin(): List<Produit>
    {
        return listOf(
            Produit(0, "Dragon Ball Fighert Z", 20.0),
            Produit(1, "TV 55\" Samsung TQ55Q70CATXXC 2023 - QLED UHD", 629.0),
            Produit(2, "Trottinette électrique Adulte – Micro X21", 499.90),
            Produit(3, "Sac de riz basmati 5kg", 12.99),
            Produit(4, "Boîte de conserve de haricots verts", 1.50),
            Produit(5, "Pack de 6 bouteilles d'eau minérale 1.5L", 3.99),
            Produit(6, "Barre de chocolat au lait", 1.20),
            Produit(7, "Sac de pommes de terre 10kg", 4.99),
            Produit(8, "Pack de 12 yaourts nature", 3.50),
            Produit(9, "Sachet de pâtes fusilli 500g", 0.80),
            Produit(10, "Fromage emmental râpé 200g", 2.50),
            Produit(11, "Paquet de chips saveur barbecue", 1.99),
            Produit(12, "Boîte de thé vert bio 50 sachets", 4.49),
            Produit(13, "Pot de confiture de fraises maison", 3.20),
            Produit(14, "Sac de carottes bio 1kg", 1.99),
            Produit(15, "Pack de 6 œufs frais de poule élevée en plein air", 2.29),
            Produit(16, "Filet de saumon frais 500g", 8.99),
            Produit(17, "Bouteille de vin rouge Bordeaux AOC", 9.50),
            Produit(18, "Paquet de biscuits sablés au chocolat", 2.79),
            Produit(19, "Pot de miel artisanal 500g", 5.99),
            )
    }

}