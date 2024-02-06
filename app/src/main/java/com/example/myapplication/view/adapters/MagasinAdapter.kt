package com.example.myapplication.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.magasin_model.Magasin

class MagasinAdapter(
    private val magasins: Map<Magasin, Double>
    , private val onMagasinClickListener: OnMagasinClickListener
) : RecyclerView.Adapter<MagasinAdapter.MagasinViewHolder>() {

    private var magasinsBackup: Map<Magasin, Double> = magasins

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MagasinViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_magasin, parent, false)
        return MagasinViewHolder(view)
    }

    override fun onBindViewHolder(holder: MagasinViewHolder, position: Int) {
        val (magasin, distance) = magasins.entries.elementAt(position)
        holder.bind(magasin, distance)
    }

    fun filterList(filterText: String) {
        magasins = if (filterText.isEmpty()) {
            magasinsBackup // Aucun filtre, afficher la liste complète
        } else {
            // Filtrer les magasins dont le nom contient le texte de filtrage
            magasinsBackup.filter { (magasin, _) ->
                magasin.nom.contains(filterText, ignoreCase = true)
            }
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = magasins.size

    inner class MagasinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nomTextView: TextView = itemView.findViewById(R.id.NomMagasin)
        private val adresseTextView: TextView = itemView.findViewById(R.id.AdresseMagasin)
        private val distanceTextView: TextView = itemView.findViewById(R.id.DistanceMagasin)
        private val imageView: ImageView = itemView.findViewById(R.id.MagasinImage)

        fun bind(magasin: Magasin, distance: Double) {
            nomTextView.text = magasin.nom
            adresseTextView.text =
                "${magasin.adresse.rue}, ${magasin.adresse.ville}, ${magasin.adresse.codePostal}"
            distanceTextView.text = "Distance: $distance km"

            // Charger l'image correspondante (vous devrez remplacer R.drawable.default_image par votre propre image)
            imageView.setImageResource(R.drawable.magasin)

            // Gérer le clic sur l'élément de la liste
            itemView.setOnClickListener {
                onMagasinClickListener.onMagasinClick(magasin)
            }
        }
    }
}
interface OnMagasinClickListener {
    fun onMagasinClick(magasin: Magasin)
}
