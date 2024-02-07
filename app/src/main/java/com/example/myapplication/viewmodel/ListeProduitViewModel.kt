package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myapplication.model.magasin_model.Produit

class ListeProduitViewModel() : ViewModel() {

    fun getListeProduits(): List<Produit> {
        return listOf(
            Produit(0, "Dragon Ball Fighter Z", 20.0, "Jeu vidéo de combat épique avec des personnages de Dragon Ball."),
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
            Produit(19, "Pot de miel artisanal 500g", 5.99, "Pot de miel artisanal de 500g, récolté avec soin pour une saveur unique.")
        )
    }

}