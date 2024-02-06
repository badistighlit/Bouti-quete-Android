package com.example.myapplication.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.myapplication.R
import com.example.myapplication.db.AppDatabase
import com.example.myapplication.db.daos.MagasinAdresse
import com.example.myapplication.db.entities.*
import com.example.myapplication.model.magasin_model.Adresse
import com.example.myapplication.model.magasin_model.Magasin
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
                db.clearAllTables()
                val entities = convertToEntities(listMagasin())
                for ((magasinEntity, adressEntity) in entities) {
                    db.MagasinsDao().insertMagasinWithAdress(magasinEntity, adressEntity)
                }
                list = db.MagasinsAdressDao().getAll()
                //magasins = convertToListMagasin(list!!)
                /*val adapter = MagasinAdapter(magasins, )
                recyclerView.layoutManager = LinearLayoutManager(this@ListeMagasin)
                recyclerView.adapter = adapter*/
            }
        }

        // le runBlocking rajoute en bouclant avec le for la list des magasins et leur adresse et les ajoute un par un dans la bdd
        // les fonctions sont toutes en bas
        // le convertToListMagasin sert a convertir ce que tu récup de la bdd
        // et le convertToEntities fait l'inverse pour insert dans la bdd
        // y a le fichier functionForBdd qui contient la même chose en bas j'ai essayé de faire le viewModel j'ai craqué j'ai tout foutu en bas
        // et j'ai ajout un id à ta liste d'adresse prc que sinon ça rentre pas dans la bdd

        backReturn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        })
    }

     fun listMagasin(): List<Magasin> {
        return listOf(
            Magasin(0, "KIKLOUTOU", Adresse(0, "01 Rue Emile Gilbert", "75012", "Paris",48.8460254,2.3732395)),
            Magasin(1, "magasin sniper", Adresse(1, "3 rue félixe faure", "75015", "Paris",48.83919906616211,2.2848060131073)),
            Magasin(2, "mingo", Adresse(2, "9 rue friant", "75014", "Paris",48.8261949,2.3247556)),
            Magasin(3, "hmimouch", Adresse(3, "135 Avenue fontainebleau", "94270", "Le Kremlin-Bicêtre",48.81172561645508,2.3617711067199707)),
            Magasin(4, "DISBA", Adresse(4, "Pl. du 4 Septembre", "13007", "Marseille",43.288631439208984,5.359172821044922)),
            Magasin(5, "La Maison des Outils", Adresse(5, "22 Rue de la République", "13001", "Marseille",43.29780960083008,5.372910022735596)),
            Magasin(6, "SuperQuincaillerie", Adresse(6, "18 Avenue de Verdun", "06500", "Menton",43.7761657,7.4944641)),
            Magasin(7, "BricoExpress", Adresse(7, "12 Rue des Fabriques", "54100", "Nancy",48.6859096,6.1877869)),
            Magasin(8, "Quincaillerie Moderne", Adresse(8, "5 Boulevard de la Liberté", "44800", "Saint-Herblain",47.2230224609375,-1.6039869785308838)),
            Magasin(9, "Outillorama", Adresse(9, "26 Rue du Bassin du Commerce", "67000", "Strasbourg",48.58171844482422,7.789248466491699)),
            Magasin(10, "BricoFrance", Adresse(10, "8 Avenue de la Gare", "33610 ", "Cestas",44.77375793457031,-0.701376736164093)),
            Magasin(11, "MegaBrico", Adresse(11, "59 Rue Claude Farrère", "69003", "Lyon",45.74305555741711,4.893425951529609)),
            Magasin(12, "Quincaillerie Central", Adresse(12, "1 Rue Georges Lefebvre", "59000", "Lille",50.6307878508149,3.075074021645734)),
        )
    }

     fun convertToListMagasin(list: List<MagasinAdresse>): List<Magasin> {
        return list.map { magasinAdresse ->
            Magasin(
                id = magasinAdresse.magasinId,
                nom = magasinAdresse.magasins_name,
                adresse = Adresse(
                    id = magasinAdresse.adressId,
                    rue = magasinAdresse.magasins_rue_name,
                    codePostal = magasinAdresse.magasins_postalCode,
                    ville = magasinAdresse.magasins_ville_name,
                    latitude = magasinAdresse.magasins_latitude,
                    longitude = magasinAdresse.magasins_longitude
                )
            )
        }
    }

    private fun convertToEntities(list: List<Magasin>): List<Pair<magasinsEntity, adressEntity>> {
        return list.map { magasin ->
            val magasinEntity = magasinsEntity(
                magasinId = magasin.id,
                magasinsName = magasin.nom,
                adressId = magasin.adresse.id
            )

            val adressEntity = adressEntity(
                adressId = magasin.adresse.id,
                magasinsRueName = magasin.adresse.rue,
                magasinsvilleId = magasin.adresse.ville,
                magasinsPostalCode = magasin.adresse.codePostal,
                magasinsLatitude = magasin.adresse.latitude,
                magasinsLongitude = magasin.adresse.longitude
            )

            Pair(magasinEntity, adressEntity)
        }
    }


}


