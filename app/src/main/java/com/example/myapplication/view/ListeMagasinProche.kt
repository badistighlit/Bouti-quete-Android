package com.example.myapplication.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.magasin_model.Magasin
import com.example.myapplication.view.ClickListeners.OnMagasinClickListener
import com.example.myapplication.view.adapters.MagasinAdapter
import com.example.myapplication.viewmodel.ApiCalcule
import com.example.myapplication.viewmodel.ListeMagasinProcheViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class ListeMagasinProche : AppCompatActivity() {

    private val listeMagasinProcheViewModel: ListeMagasinProcheViewModel by viewModel()
    private val apiCalcule: ApiCalcule by lazy { ApiCalcule(listeMagasinProcheViewModel) }
      @SuppressLint("SuspiciousIndentation", "SetTextI18n")
      override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liste_magasin_proche)
        val backReturn: Button = findViewById(R.id.backReturn)
          val searchItem: SearchView = findViewById(R.id.SearchItem)

        val nom = intent.getStringExtra("NOM")
        val prenom = intent.getStringExtra("PRENOM")
        val rue = intent.getStringExtra("RUE")
        val ville = intent.getStringExtra("VILLE")
        val postalCode = intent.getStringExtra("CODE POSTAL")
        val adresse = "$rue $ville $postalCode"

        val textView = findViewById<TextView>(R.id.Salutation)
        textView.text = "BONJOUR " + nom + " " + prenom + " votre adresse est la suivante : \n" + adresse +
                "\nVoici la liste des magasins les plus proche de chez vous"

          lifecycleScope.launch {

              val magasins = apiCalcule.getPlusProche(adresse)
              val adapter = MagasinAdapter(magasins, object : OnMagasinClickListener {
                  override fun onMagasinClick(magasin: Magasin) {
                      // Action à effectuer lors du clic sur un magasin
                      val intent = Intent(this@ListeMagasinProche, ListeProduit::class.java).apply {
                          putExtra("idMagasin", magasin.id)
                      }

                      startActivity(intent)
                  }
              })
              val recyclerView: RecyclerView = findViewById(R.id.ListerecyclerView)
              recyclerView.layoutManager = LinearLayoutManager(this@ListeMagasinProche)
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


          backReturn.setOnClickListener(View.OnClickListener {
              val intent = Intent(this, MainActivity::class.java)
              startActivity(intent)
              finish()
          })
    }


}