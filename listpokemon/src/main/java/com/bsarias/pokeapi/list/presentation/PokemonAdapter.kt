package com.bsarias.pokeapi.list.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bsarias.pokeapi.list.databinding.ItemPokemonBinding
import com.bumptech.glide.Glide
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.BitmapPalette.Profile.MUTED
import com.github.florent37.glidepalette.GlidePalette


class PokemonAdapter constructor(
    private val onClick: OnClickItemList,
    private val context: Context
) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    var pokemons: List<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = pokemons[position].split("-")
        val id = pokemon[0]
        val name = pokemon[1]

        holder.bind(id, name, context)
        holder.itemView.setOnClickListener {
            onClick.onClickItem(name)
        }
    }

    override fun getItemCount(): Int = pokemons.size

    class ViewHolder(private val binding: ItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(id: String, name: String, context: Context) {
            binding.pokemonName.text = name
            val idText = "#$id"
            binding.pokemonId.text = idText
            val url = "$IMAGE_LINK${id.toInt()}.png"
            glideImage(url, context)
        }

        private fun glideImage(url: String, context: Context) {
            Glide.with(context).load(url)
                .listener(
                    GlidePalette.with(url)
                        .use(MUTED)
                        .intoBackground(binding.card, BitmapPalette.Swatch.RGB)
                        .crossfade(true)
                )
                .into(binding.pokemonImage)
        }

    }
}

private const val IMAGE_LINK =
    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"