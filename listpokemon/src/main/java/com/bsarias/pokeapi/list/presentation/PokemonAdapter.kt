package com.bsarias.pokeapi.list.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bsarias.pokeapi.list.databinding.ItemPokemonBinding

class PokemonAdapter : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    var pokemons: List<String> = ArrayList()
    private lateinit var onClick: OnClickItemList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = pokemons[position]
        holder.bind(pokemon)
        holder.itemView.setOnClickListener {
            onClick.onClickItem(pokemon)
        }
    }

    override fun getItemCount(): Int = pokemons.size

    fun setOnClick(onClickItemList: OnClickItemList) {
        onClick = onClickItemList
    }

    class ViewHolder(private val binding: ItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon: String) {
            binding.pokemonName.text = pokemon
        }
    }
}