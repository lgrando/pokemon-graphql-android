package com.grando.pokemonapp.presentation.views.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.grando.pokemonapp.R
import com.grando.pokemonapp.databinding.ItemPokemonBinding
import com.grando.pokemonapp.domain.Pokemon
import com.grando.pokemonapp.interactors.GetCatchedPokemons

@SuppressLint("NotifyDataSetChanged")
class PokemonAdapter(
    private var listItems: MutableList<Pokemon> = mutableListOf(),
    private val listener: (Pokemon, View) -> Unit
) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = ItemPokemonBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = listItems[position]
        holder.itemView.tag = pokemon
        holder.bind(pokemon, listener)
    }

    override fun getItemCount() = listItems.size

    fun resetAdapter() {
        listItems.clear()
        notifyDataSetChanged()
    }

    fun submitList(items: List<Pokemon>) {
        items.forEach { pokemon ->
            val isDuplicated = listItems.find { it.id == pokemon.id } != null
            if (!isDuplicated) listItems.add(pokemon)
        }
        notifyItemInserted(listItems.size)
    }

    class PokemonViewHolder(private val binding: ItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pokemon: Pokemon, listener: (Pokemon, View) -> Unit) {
            binding.pokemon = pokemon
            binding.cvPokemon.setOnClickListener {
                listener(pokemon, it)
            }
        }
    }
}