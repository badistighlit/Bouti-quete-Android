package com.example.myapplication

import ApiCaller
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.magasin_model.Adresse
import com.example.myapplication.model.magasin_model.Magasin
import com.example.myapplication.network.LocalisationApi
import com.example.myapplication.view.MainActivity
import com.example.myapplication.view.adapters.MagasinAdapter

val apiCaller = ApiCaller()

class ListeMagasinProche : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liste_magasin_proche)
        val backReturn: Button = findViewById(R.id.backReturn)

        val nom = intent.getStringExtra("NOM")
        val prenom = intent.getStringExtra("PRENOM")
        /*val rue = intent.getStringExtra("RUE")
        val ville = intent.getStringExtra("VILLE")
        val postalCode = intent.getStringExtra("CODE POSTAL")*/
        val rue = "57 Rue Vallière"
        val ville = "Nogent Sur Oise"
        val postalCode = "60180"
        val adresse = convertEspacesEnPlus("$rue+$ville+$postalCode")
        val adresse2 = "01 Rue Emile Gilbert 75012 Paris"

        var latInit:String
        var longInit:String

        val textView = findViewById<TextView>(R.id.Salutation)
        val textView2 = findViewById<TextView>(R.id.textView)
        //textView.text = "BONJOUR " + nom + " " + prenom + " votre adresse est la suivante : \n" + adresse +
        //        "\nVoici la liste des magasins les plus proche de chez vous"
        /*val magasins = listOf(
            Magasin(1, "KIKLOUTOU", Adresse("01 Rue Emile Gilbert", "75012", "Paris")),
            Magasin(2, "magasin sniper", Adresse("3 rue félixe faure", "75015", "Paris")),
            Magasin(3, "mingo", Adresse("9 rue friant", "75014", "Paris")),
            Magasin(4, "hmimouch", Adresse("135 Avenue fontainebleau", "94270", "Le Kremlin-Bicêtre")),
            Magasin(5, "DISBA", Adresse("Pl. du 4 Septembre", "13007", "Marseille"))
        )

        val adapter = MagasinAdapter(magasins)
        val recyclerView: RecyclerView = findViewById(R.id.ListerecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter*/

        textView.text = convertPlusEnEspaces("la distance que sépare les adresses :\n" +
                "$adresse et $adresse2 est de : \n")

        apiCaller.performApiCall(adresse) { lat, lng ->
            latInit = lat
            longInit = lng

            apiCaller.performApiCall(adresse2) { lat, lng ->
                textView2.text = apiCaller.getDistance(latInit.toDouble(), longInit.toDouble(), lat.toDouble(), lng.toDouble()).toString() + " kilomètre"
            }
        }




        backReturn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        })
    }

    fun convertEspacesEnPlus(chaine: String): String {
        return chaine.replace(" ", "+")
    }

    fun convertPlusEnEspaces(chaine: String): String {
        return chaine.replace("+", " ")
    }
}