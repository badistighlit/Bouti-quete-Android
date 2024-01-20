package com.example.myapplication
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.magasin_model.Magasin
import com.example.myapplication.view.adapters.MagasinAdapter

class ListeMagasin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liste_magasin)
        val recyclerView: RecyclerView = findViewById(R.id.ListerecyclerView)
        val magasins = listOf(
            Magasin(1, "KIKLOUTOU", "01 Rue Emile Gilbert, 75012 Paris"),
            Magasin(2, "magasin sniper", "3 rue félixe faure, 75015 Paris"),
            Magasin(3, "mingo", "9 rue friant , 75014 Paris"),
            Magasin(4,"hmimouch","135 Avenue fontainebleau, 94270 Le Kremlin-Bicêtre"),
            Magasin(5,"DISBA"," Pl. du 4 Septembre, 13007 Marseille"),
            Magasin(6, "La Maison des Outils", "22 Rue de la République, 13001 Marseille"),
            Magasin(7, "SuperQuincaillerie", "18 Avenue de Verdun, 06000 Nice"),
            Magasin(8, "BricoExpress", "12 Rue des Fabriques, 31000 Toulouse"),
            Magasin(9, "Quincaillerie Moderne", "5 Boulevard de la Liberté, 44000 Nantes"),
            Magasin(10, "Outillorama", "26 Rue du Commerce, 67000 Strasbourg"),
            Magasin(11, "BricoFrance", "8 Place de la Gare, 33000 Bordeaux"),
            Magasin(12, "OutilLand", "7 Rue des Artisans, 35000 Rennes"),
            Magasin(13, "MegaBrico", "14 Rue du Bricolage, 69000 Lyon"),
            Magasin(14, "Quincaillerie Central", "32 Rue des Forges, 59000 Lille"),
            Magasin(15, "BricoPlus", "11 Avenue des Bâtisseurs, 54000 Nancy")

        )

        val adapter = MagasinAdapter(magasins)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

    }
}


