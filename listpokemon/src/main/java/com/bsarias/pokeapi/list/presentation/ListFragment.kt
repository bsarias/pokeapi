package com.bsarias.pokeapi.list.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bsarias.pokeapi.list.R
import com.bsarias.pokeapi.list.databinding.FragmentListBinding
import com.bsarias.pokeapi.list.framework.di.ListComponentProvider
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bsarias.pokeapi.core.domain.ListPokemon
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject


class ListFragment : Fragment() {

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

    private fun getState(model: ListViewModel.ListViewState){
        when(model) {
            is ListViewModel.ListViewState.Loading -> {
                Snackbar.make(binding.root, "Loading", Snackbar.LENGTH_LONG).show()
            }
            is ListViewModel.ListViewState.Success -> {
                loadList(model.listPokemon)
            }
            is ListViewModel.ListViewState.Error -> {

            }

        }
    }

    private fun loadList(pokemons: ListPokemon){
        val adapter = PokemonAdapter()
        binding.recyclerPokemons.adapter = adapter
        adapter.pokemons = pokemons.results.toList()
    }

}
