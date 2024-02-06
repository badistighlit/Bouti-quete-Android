package com.example.myapplication.view.adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.magasin_model.Magasin
import com.example.myapplication.model.magasin_model.Produit


class ProduitAdapter(private val produit: List<Produit>) :
    RecyclerView.Adapter<ProduitAdapter.ProduitViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProduitViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProduitViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProduitViewHolder, position: Int) {
        val produit = produit[position]
        holder.bind(produit)
    }

    override fun getItemCount(): Int = produit.size

    class ProduitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nomTextView: TextView = itemView.findViewById(R.id.NomProduit)
        private val prixTextView: TextView = itemView.findViewById(R.id.prixProduit)
        private val imageView: ImageView = itemView.findViewById(R.id.ProduitImage)

        fun bind(produit: Produit) {
            nomTextView.text = produit.nom
            prixTextView.text = "${produit.prix}€"

        }
    }
}

