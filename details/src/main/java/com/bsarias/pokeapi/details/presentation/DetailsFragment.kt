package com.bsarias.pokeapi.details.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bsarias.pokeapi.core.domain.Pokemon
import com.bsarias.pokeapi.details.databinding.FragmentDetailsBinding
import com.bsarias.pokeapi.details.framework.di.DetailsComponentProvider
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val args: DetailsFragmentArgs by navArgs()

    @Inject
    lateinit var viewModel: DetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val v = binding.root
        (requireActivity().applicationContext as DetailsComponentProvider).provideDetailsComponent()
            .inject(this)
        binding.pokemonName.text = args.pokemonName

        viewModel.getDetail(args.pokemonName).observe(requireActivity(), Observer(::getState))
        return v
    }

    private fun getState(model: DetailsViewState) {
        binding.progress.visibility = View.GONE
        when (model) {
            is DetailsViewState.Loading -> {
                binding.progress.visibility = View.VISIBLE
            }
            is DetailsViewState.Success -> {
                loadPokemonData(model.pokemon)
            }
            is DetailsViewState.Error -> {
                Snackbar.make(binding.root, model.error, Snackbar.LENGTH_LONG).show()
            }

        }
    }

    private fun loadPokemonData(pokemon: Pokemon) {
        binding.pokemonName.text = pokemon.name
        binding.pokemonHeight.text = pokemon.height.toString()
        binding.pokemonWeight.text = pokemon.weight.toString()
        binding.pokemonTypes.text = pokemon.types
        Glide.with(requireActivity())
            .load(pokemon.officialArtwork)
            .centerCrop()
            .into(binding.pokemonImage);
    }
}