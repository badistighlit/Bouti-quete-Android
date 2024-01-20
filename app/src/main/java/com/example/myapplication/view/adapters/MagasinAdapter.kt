package com.example.myapplication.view.adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.magasin_model.Magasin


class MagasinAdapter(private val magasins: List<Magasin>) :
    RecyclerView.Adapter<MagasinAdapter.MagasinViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MagasinViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_magasin, parent, false)
        return MagasinViewHolder(view)
    }

    override fun onBindViewHolder(holder: MagasinViewHolder, position: Int) {
        val magasin = magasins[position]
        holder.bind(magasin)
    }

    override fun getItemCount(): Int = magasins.size

    class MagasinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nomTextView: TextView = itemView.findViewById(R.id.NomMagasin)
        private val adresseTextView: TextView = itemView.findViewById(R.id.AdresseMagasin)

        fun bind(magasin: Magasin) {
            nomTextView.text = magasin.nom
            adresseTextView.text = magasin.adresse
        }
    }
}
