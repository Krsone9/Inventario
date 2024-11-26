package com.krsone9.inventario.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krsone9.inventario.data.ProductosClass
import com.krsone9.inventario.databinding.ItemProductoBinding
import com.squareup.picasso.Picasso

class ProductosAdapter (var items: List<ProductosClass>, val onItemClick: (Int) -> Unit) : RecyclerView.Adapter<ProductosViewHolder>() {
    override fun onBindViewHolder(holder: ProductosViewHolder, position: Int) {
        val Producto_selected = items[position]

        holder.render(Producto_selected)
        holder.itemView.setOnClickListener { onItemClick(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductosViewHolder {
       val binding = ItemProductoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductosViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateItems(items: List<ProductosClass>) {
        this.items = items
        notifyDataSetChanged()
    }
}

class ProductosViewHolder(val binding: ItemProductoBinding) : RecyclerView.ViewHolder(binding.root) {

    fun render(producto: ProductosClass) {
        binding.ProductoTextView.text = producto.title
        Picasso.get().load(producto.image).into(binding.ProductoImageView)

    }
}