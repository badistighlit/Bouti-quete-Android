package com.example.myapplication.view.Extensions

import android.content.Context
import android.content.Intent
import com.example.myapplication.R
import com.example.myapplication.RenseigneAdresseActivity
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
                val intent = Intent(context, RenseigneAdresseActivity::class.java)
                context.startActivity(intent)
                true
            }
            else -> {
                val intent = Intent(context, RenseigneAdresseActivity::class.java)
                context.startActivity(intent)
                true
            }
        }
    }
}
