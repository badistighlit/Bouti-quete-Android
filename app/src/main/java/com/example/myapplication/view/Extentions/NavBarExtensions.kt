package com.example.myapplication.view.Extentions

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.myapplication.R
import com.example.myapplication.view.RenseigneAdresseActivity
import com.example.myapplication.view.ListeMagasin
import com.example.myapplication.view.ListeProduit
import com.google.android.material.bottomnavigation.BottomNavigationView

fun BottomNavigationView.setupBottomNavigation(context: Context) {

    this.setOnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.ListeProduit -> {
                val intent = Intent(context, ListeProduit::class.java)
                context.startActivity(intent)
                true
            }
            R.id.ListeMagasin -> {
                val intent = Intent(context, ListeMagasin::class.java)
                context.startActivity(intent)
                true
            }
            R.id.RenseigneAdresseActivity -> {
                try {
                    val intent = Intent(context, RenseigneAdresseActivity::class.java)
                    context.startActivity(intent)
                    true
                } catch (e: ActivityNotFoundException) {
                    Log.e("Erreur", "L'activité RenseigneAdresseActivity n'a pas été trouvée : ${e.message}")
                    false
                }

            }
            else -> {
                val intent = Intent(context, RenseigneAdresseActivity::class.java)
                context.startActivity(intent)
                true
            }
        }
    }
}
