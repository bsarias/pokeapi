package com.bsarias.pokeapi.list.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bsarias.pokeapi.list.R
import com.bsarias.pokeapi.list.databinding.ItemPokemonBinding

class PokemonAdapter : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    var pokemons: List<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = pokemons[position]
        holder.bind(pokemon)
    }

    override fun getItemCount(): Int = pokemons.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemPokemonBinding.bind(view)

        fun bind(pokemon: String) {
            binding.pokemonName.text = pokemon
        }
    }
}