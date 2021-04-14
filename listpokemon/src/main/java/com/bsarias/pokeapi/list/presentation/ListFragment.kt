package com.bsarias.pokeapi.list.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bsarias.pokeapi.core.domain.ListPokemon
import com.bsarias.pokeapi.core.framework.navigation.InternalNavigation
import com.bsarias.pokeapi.list.databinding.FragmentListBinding
import com.bsarias.pokeapi.list.framework.di.ListComponentProvider
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject


class ListFragment : Fragment(), OnClickItemList {

    private lateinit var binding: FragmentListBinding

    @Inject
    lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        val v = binding.root
        (activity?.applicationContext as ListComponentProvider).provideListComponent().inject(this)

        viewModel.getPokemons().observe(requireActivity(), Observer(::getState))
        return v
    }

    private fun getState(model: ListViewModel.ListViewState) {
        when (model) {
            is ListViewModel.ListViewState.Loading -> {
                Snackbar.make(binding.root, "Loading", Snackbar.LENGTH_LONG).show()
            }
            is ListViewModel.ListViewState.Success -> {
                loadList(model.listPokemon)
            }
            is ListViewModel.ListViewState.Error -> {
                Snackbar.make(binding.root, model.error, Snackbar.LENGTH_LONG).show()
            }

        }
    }

    private fun loadList(pokemons: ListPokemon) {
        val adapter = PokemonAdapter()
        binding.recyclerPokemons.adapter = adapter
        adapter.pokemons = pokemons.results
        adapter.setOnClick(this)
    }

    override fun onClickItem(pokemonName: String) {
        val deepLink = "${InternalNavigation.DETAILS}$pokemonName".toUri()
        findNavController().navigate(deepLink)
    }

}
