package com.example.myapplication.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.myapplication.R
import com.example.myapplication.db.AppDatabase
import com.example.myapplication.db.daos.MagasinAdresse
import com.example.myapplication.db.entities.*
import com.example.myapplication.model.magasin_model.Adresse
import com.example.myapplication.model.magasin_model.Magasin
import com.example.myapplication.view.adapters.MagasinAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ListeMagasin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liste_magasin)
        val recyclerView: RecyclerView = findViewById(R.id.ListerecyclerView)
        val backReturn: Button = findViewById(R.id.backReturn)

        /*val magasins = listOf(
            Magasin(1, "KIKLOUTOU", Adresse("01 Rue Emile Gilbert", "75012", "Paris")),
            Magasin(2, "magasin sniper", Adresse("3 rue félixe faure", "75015", "Paris")),
            Magasin(3, "mingo", Adresse("9 rue friant", "75014", "Paris")),
            Magasin(4, "hmimouch", Adresse("135 Avenue fontainebleau", "94270", "Le Kremlin-Bicêtre")),
            Magasin(5, "DISBA", Adresse("Pl. du 4 Septembre", "13007", "Marseille")),
            Magasin(6, "La Maison des Outils", Adresse("22 Rue de la République", "13001", "Marseille")),
            Magasin(7, "SuperQuincaillerie", Adresse("18 Avenue de Verdun", "06000", "Nice")),
            Magasin(8, "BricoExpress", Adresse("12 Rue des Fabriques", "31000", "Toulouse")),
            Magasin(9, "Quincaillerie Moderne", Adresse("5 Boulevard de la Liberté", "44000", "Nantes")),
            Magasin(10, "Outillorama", Adresse("26 Rue du Commerce", "67000", "Strasbourg")),
            Magasin(11, "BricoFrance", Adresse("8 Place de la Gare", "33000", "Bordeaux")),
            Magasin(12, "OutilLand", Adresse("7 Rue des Artisans", "35000", "Rennes")),
            Magasin(13, "MegaBrico", Adresse("14 Rue du Bricolage", "69000", "Lyon")),
            Magasin(14, "Quincaillerie Central", Adresse("32 Rue des Forges", "59000", "Lille")),
            Magasin(15, "BricoPlus", Adresse("11 Avenue des Bâtisseurs", "54000", "Nancy"))
        )*/

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).fallbackToDestructiveMigration().build()

        val newAdress = adressEntity(
            adressId = 0,
            magasinsRueName = "01 Rue Emile Gilbert",
            magasinsvilleId = "Paris",
            magasinsPostalCode = "75012",
            magasinsLatitude = 48.8460254,
            magasinsLongitude = 2.3732395
        )

        val newMagasin = magasinsEntity(
            magasinId = 0,
            magasinsName = "KIKLOUTOU",
            adressId = 0
        )

        var list: List<MagasinAdresse>?

        runBlocking {
            launch(Dispatchers.IO) {
                db.MagasinsDao().insertMagasinWithAdress(newMagasin, newAdress)
                list = db.MagasinsAdressDao().getAll()
                val magasins = convertToListMagasin(list!!)
                /*val adapter = MagasinAdapter(magasins, )
                recyclerView.layoutManager = LinearLayoutManager(this@ListeMagasin)
                recyclerView.adapter = adapter*/
            }
        }

        backReturn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        })
    }

    private fun convertToListMagasin(list: List<MagasinAdresse>): List<Magasin> {
        return list.map { magasinAdresse ->
            Magasin(
                id = magasinAdresse.magasinId,
                nom = magasinAdresse.magasins_name,
                adresse = Adresse(
                    rue = magasinAdresse.magasins_rue_name,
                    codePostal = magasinAdresse.magasins_postalCode,
                    ville = magasinAdresse.magasins_ville_name,
                    latitude = magasinAdresse.magasins_latitude,
                    longitude = magasinAdresse.magasins_longitude
                )
            )
        }
    }


}


