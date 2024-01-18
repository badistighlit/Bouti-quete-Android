package com.example.myapplication

import android.content.Intent
import android.os.Bundle
<<<<<<< HEAD
import android.widget.Button
=======
import android.util.Log
>>>>>>> 0e914cd (test connexion localhost rafailed but good method)
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.Main) {
            val data = withContext(Dispatchers.IO) {
                // Appeler la fonction de gestion de la base de données dans un contexte IO
                DatabaseManager().testerConnexion("jdbc:mysql://127.0.0.1/android")
            }
            println(data)
        }

        val imageView = findViewById<ImageView>(R.id.imageView2)

        Glide.with(this)
            .asGif() // Indique que l'image est un GIF
            .load(R.drawable.logo)
            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.DATA)) // Cache l'image en mémoire
            .into(imageView)
        val buttonListeMagasin = findViewById<Button>(R.id.buttonListeMagasins)
        buttonListeMagasin.setOnClickListener {
            val intent = Intent(this, ListeMagasin::class.java)
            startActivity(intent)
        }
        val btnRenseigneAdresse = findViewById<Button>(R.id.btnRenseigneAdresse)
        btnRenseigneAdresse.setOnClickListener {
            val intent = Intent(this, RenseigneAdresseActivity::class.java)
            startActivity(intent)

        }

    }

    private fun logData(data: List<String>) {
        for (item in data) {
            Log.d("DataLog", item)
        }
    }
}
