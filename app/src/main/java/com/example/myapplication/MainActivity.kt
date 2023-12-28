package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
}
