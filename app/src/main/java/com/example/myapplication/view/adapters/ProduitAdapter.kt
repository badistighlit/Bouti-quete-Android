package com.example.myapplication.view.adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.magasin_model.Produit
import com.example.myapplication.view.ClickListeners.OnProduitClickListener


class ProduitAdapter(
    private var produits: List<Produit>,

    private val onProduitClickListener: OnProduitClickListener
):
    RecyclerView.Adapter<ProduitAdapter.ProduitViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProduitViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProduitViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProduitViewHolder, position: Int) {
        val produit = produits[position]
        holder.bind(produit)
        holder.itemView.setOnClickListener {
            onProduitClickListener.OnProduitClick(produit)
        }
    }
    fun filterList(filterText: String) {
        produits = if (filterText.isEmpty()) {
            produits
        } else {
            // Filtre filtrage
            produits.filter { produit->
                produit.nom.contains(filterText, ignoreCase = true)
            }
        }
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int = produits.size

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




