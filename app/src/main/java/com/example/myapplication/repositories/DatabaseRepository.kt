package com.example.myapplication.repositories


import com.example.myapplication.db.AppDatabase
import com.example.myapplication.db.daos.MagasinAdresse
import com.example.myapplication.db.entities.adressEntity
import com.example.myapplication.db.entities.magasinsEntity
import com.example.myapplication.model.magasin_model.Adresse
import com.example.myapplication.model.magasin_model.Magasin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class DatabaseRepository(private val db: AppDatabase) {
    lateinit var list: List<Magasin>
    fun build() {



        runBlocking {
            launch(Dispatchers.IO) {
                db.clearAllTables()
                val entities = convertToEntities(listMagasin())
                for ((magasinEntity, adressEntity) in entities) {
                    db.MagasinsDao().insertMagasinWithAdress(magasinEntity, adressEntity)
                }
                list = convertToListMagasin(db.MagasinsAdressDao().getAll());

            }
        }


    }
     fun getMagasins():List<Magasin>{
        return this.list;
    }


    private fun listMagasin(): List<Magasin> {
        return listOf(
            Magasin(0, "KIKLOUTOU", Adresse(0, "01 Rue Emile Gilbert", "75012", "Paris",48.8460254,2.3732395)),
            Magasin(1, "magasin sniper", Adresse(1, "3 rue félixe faure", "75015", "Paris",48.83919906616211,2.2848060131073)),
            Magasin(2, "mingo", Adresse(2, "9 rue friant", "75014", "Paris",48.8261949,2.3247556)),
            Magasin(3, "mimouch", Adresse(3, "135 Avenue fontainebleau", "94270", "Le Kremlin-Bicêtre",48.81172561645508,2.3617711067199707)),
            Magasin(4, "DISBA", Adresse(4, "Pl. du 4 Septembre", "13007", "Marseille",43.288631439208984,5.359172821044922)),
            Magasin(5, "La Maison des Outils", Adresse(5, "22 Rue de la République", "13001", "Marseille",43.29780960083008,5.372910022735596)),
            Magasin(6, "SuperQuincaillerie", Adresse(6, "18 Avenue de Verdun", "06500", "Menton",43.7761657,7.4944641)),
            Magasin(7, "BricoExpress", Adresse(7, "12 Rue des Fabriques", "54100", "Nancy",48.6859096,6.1877869)),
            Magasin(8, "Quincaillerie Moderne", Adresse(8, "5 Boulevard de la Liberté", "44800", "Saint-Herblain",47.2230224609375,-1.6039869785308838)),
            Magasin(9, "Outillorama", Adresse(9, "26 Rue du Bassin du Commerce", "67000", "Strasbourg",48.58171844482422,7.789248466491699)),
            Magasin(10, "BricoFrance", Adresse(10, "8 Avenue de la Gare", "33610 ", "Cestas",44.77375793457031,-0.701376736164093)),
            Magasin(11, "MegaBrico", Adresse(11, "59 Rue Claude Farrère", "69003", "Lyon",45.74305555741711,4.893425951529609)),
            Magasin(12, "Quincaillerie Central", Adresse(12, "1 Rue Georges Lefebvre", "59000", "Lille",50.6307878508149,3.075074021645734)),
            Magasin(13,"Hanout du turfu", Adresse(13,"Bd Krim Belkacem","06000","Bejaia",36.7514177,5.0552298)),
            Magasin(14,"Brico papa" ,Adresse(14,"Route Forestière du Lac Noir","16110","Saint-Projet-Saint-Constant",45.74329686783661,0.32707110950442875))
        )
    }

    private fun convertToListMagasin(list: List<MagasinAdresse>): List<Magasin> {
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