package com.krsone9.inventario.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krsone9.inventario.databinding.ItemCategoriaBinding

class CategoriasAdapter (var items: List<String>, val onItemClick: (Int) -> Unit) : RecyclerView.Adapter<CategoriaViewHolder>() {
        override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
            val Categoria_selected = items[position]

            holder.render(Categoria_selected)
            holder.itemView.setOnClickListener { onItemClick(position)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaViewHolder {
            val binding = ItemCategoriaBinding.inflate(LayoutInflater.from(parent.context),parent, false)
            return CategoriaViewHolder(binding)
        }

        override fun getItemCount(): Int {
            return items.size
        }

        fun updateItems(items: List<String>) {
            this.items = items
            notifyDataSetChanged()
        }
    }

    class CategoriaViewHolder(val binding: ItemCategoriaBinding) : RecyclerView.ViewHolder(binding.root) {

        fun render(categoria: String) {
            binding.CategoriasTextView.text = categoria
            //Picasso.get().load(superhero.image.url).into(binding.avatarImageView)
        }
    }