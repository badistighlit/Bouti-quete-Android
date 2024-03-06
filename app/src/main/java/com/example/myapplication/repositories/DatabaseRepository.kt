package com.example.myapplication.repositories


import com.example.myapplication.db.AppDatabase
import com.example.myapplication.db.entities.ProduitEntity
import com.example.myapplication.db.entities.adressEntity
import com.example.myapplication.db.entities.MagasinProduitEntity
import com.example.myapplication.db.entities.magasinsEntity
import com.example.myapplication.model.magasin_model.Adresse
import com.example.myapplication.model.magasin_model.Magasin
import com.example.myapplication.model.magasin_model.MagasinAdresse
import com.example.myapplication.model.magasin_model.Produit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class DatabaseRepository(private val db: AppDatabase) {
    lateinit var listMagasin: List<Magasin>
    lateinit var listProduits: List<Produit>

    fun buildIfNeeded() {
        runBlocking {
            launch(Dispatchers.IO) {
                if (db.MagasinsDao().getAllMagasins() == null) {
                    build()
                }
            }
        }
    }

    private fun build() {
            runBlocking {
                launch(Dispatchers.IO) {
                    db.clearAllTables()

                    val entitiesProduits = convertProductToEntities(getListeProduits())
                    for (produitEntities in entitiesProduits){
                        db.ProduitDao().insertProduit((produitEntities))
                    }
                    listProduits = convertEntitiesToProduct(db.ProduitDao().getAllproduits())


                    // Insérer les produits pour le magasin
                    listMagasin = convertToListMagasin(db.MagasinsAdressDao().getAll())
                    val entities = convertMagasinToEntities(listMagasin())
                    var i: Int = 0
                    for ((magasinEntity, adressEntity) in entities) {
                        val magasinId = db.MagasinsDao().insertMagasinWithAdress(magasinEntity, adressEntity)
                        val selectedProducts = listProduits.shuffled().take((5..10).random())
                        for (produit in selectedProducts) {
                            val produitMagasinEntity = MagasinProduitEntity(
                                i,
                                produitId = produit.id,
                                magasinId = magasinId
                            )
                            db.MagasinProduitDao().insertMagasinProduit(produitMagasinEntity)
                            i++
                        }
                    }
                }
            }
    }

    fun getMagasins():List<Magasin>{
        lateinit var listeMagasins: List<Magasin>
        runBlocking {
            launch(Dispatchers.IO) {
                listeMagasins = convertToListMagasin(db.MagasinsAdressDao().getAll())
            }
        }
        return listeMagasins
    }

    fun getProduits(): List<Produit>{
        return this.listProduits
    }

    fun getProduitsByMagasinId(id: Int): List<Produit>{
        lateinit var listProduitForMagasin: List<Produit>
        runBlocking {
            launch(Dispatchers.IO) {
                listProduitForMagasin = convertEntitiesToProduct(db.MagasinProduitDao().getProductsForMagasin(id))
            }
        }
        return listProduitForMagasin
    }

    fun getMagasinsByProduitId(id: Int): List<Magasin>{
        val magasinsFromDao: List<magasinsEntity> = db.MagasinProduitDao().getMagasinsForProduct(id)
        val magasinIdsFromDao = magasinsFromDao.map { it.magasinId }

        return listMagasin.filter { magasin -> magasinIdsFromDao.contains(magasin.id) }
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

    private fun convertMagasinToEntities(list: List<Magasin>): List<Pair<magasinsEntity, adressEntity>> {
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

    fun convertProductToEntities(list: List<Produit>): List<ProduitEntity> {
        return list.map {produit ->
            ProduitEntity(
                produitId = produit.id,
                produitName = produit.nom,
                produitPrix = produit.prix,
                produitDescription = produit.description
            )
        }
    }

    fun convertEntitiesToProduct(list: List<ProduitEntity>): List<Produit> {
        return list.map {produitEntity ->
            Produit(
                id = produitEntity.produitId,
                nom = produitEntity.produitName,
                prix = produitEntity.produitPrix,
                description = produitEntity.produitDescription
            )
        }
    }

    fun getListeProduits(): List<Produit> {
        return listOf(
            Produit(1, "TV 55\" Samsung TQ55Q70CATXXC 2023 - QLED UHD", 629.0, "Téléviseur Samsung haute qualité avec écran QLED de 55 pouces."),
            Produit(2, "Trottinette électrique Adulte – Micro X21", 499.90, "Trottinette électrique pliable et légère pour les déplacements urbains."),
            Produit(3, "Sac de riz basmati 5kg", 12.99, "Sac de riz basmati de qualité supérieure pour des plats délicieux."),
            Produit(4, "Boîte de conserve de haricots verts", 1.50, "Haricots verts frais conservés dans une boîte pratique."),
            Produit(5, "Pack de 6 bouteilles d'eau minérale 1.5L", 3.99, "Eau minérale naturelle en pack économique."),
            Produit(6, "Barre de chocolat au lait", 1.20, "Délicieuse barre de chocolat au lait pour une pause gourmande."),
            Produit(7, "Sac de pommes de terre 10kg", 4.99, "Sac de pommes de terre de qualité pour diverses préparations culinaires."),
            Produit(8, "Pack de 12 yaourts nature", 3.50, "Pack économique de yaourts nature pour une alimentation saine."),
            Produit(9, "Sachet de pâtes fusilli 500g", 0.80, "Sachet de pâtes fusilli de blé dur pour des repas savoureux."),
            Produit(10, "Fromage emmental râpé 200g", 2.50, "Fromage emmental râpé pour agrémenter vos plats préférés."),
            Produit(11, "Paquet de chips saveur barbecue", 1.99, "Chips croustillantes avec une délicieuse saveur barbecue."),
            Produit(12, "Boîte de thé vert bio 50 sachets", 4.49, "Thé vert bio de haute qualité en boîte de 50 sachets."),
            Produit(13, "Pot de confiture de fraises maison", 3.20, "Confiture de fraises maison, idéale pour le petit-déjeuner."),
            Produit(14, "Sac de carottes bio 1kg", 1.99, "Sac de carottes bio fraîches pour une alimentation équilibrée."),
            Produit(15, "Pack de 6 œufs frais de poule élevée en plein air", 2.29, "Pack de 6 œufs frais provenant de poules élevées en plein air."),
            Produit(16, "Filet de saumon frais 500g", 8.99, "Filet de saumon frais pour des repas sains et délicieux."),
            Produit(17, "Bouteille de vin rouge Bordeaux AOC", 9.50, "Bouteille de vin rouge de qualité supérieure, AOC Bordeaux."),
            Produit(18, "Paquet de biscuits sablés au chocolat", 2.79, "Biscuits sablés au chocolat pour une pause sucrée."),
            Produit(19, "Pot de miel artisanal 500g", 5.99, "Pot de miel artisanal de 500g, récolté avec soin pour une saveur unique."),
            Produit(20, "Dragon Ball Fighter Z", 20.0, "Jeu vidéo de combat épique avec des personnages de Dragon Ball.")
        )
    }

    fun getAllProducts(): List<Produit> {
        lateinit var truc: List<ProduitEntity>
        runBlocking {
            launch(Dispatchers.IO) {
                truc = db.ProduitDao().getAllproduits()
            }
        }
        return convertEntitiesToProduct(truc)
    }

}