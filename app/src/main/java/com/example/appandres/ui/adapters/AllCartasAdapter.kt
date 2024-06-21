package com.example.appandres.ui.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.appandres.R
import com.example.appandres.data.network.entity.allCartas.Item
import com.example.appandres.databinding.ItemsAllCartasBinding

class AllCartasAdapter : RecyclerView.Adapter<AllCartasAdapter.CartasViewHolder>() {

    var listCartas: List<Item> = emptyList()
    class CartasViewHolder(view : View) : RecyclerView.ViewHolder(view){
        private val binding = ItemsAllCartasBinding.bind(view)
        fun render(data : Item){
            binding.cartaName.text = data.name
            binding.cartaCalidad.text = data.rarity
            binding.cartaImage.load(data.iconUrls.medium){
                placeholder(R.drawable.ic_launcher_background)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartasViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CartasViewHolder(
            inflater.inflate(
                R.layout.items_all_cartas,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return  listCartas.size
    }

    override fun onBindViewHolder(holder: CartasViewHolder, position: Int) {
        holder.render(
            listCartas[position]
        )
    }
}