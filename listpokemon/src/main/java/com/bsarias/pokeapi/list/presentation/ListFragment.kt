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
import javax.inject.Inject


class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val v = binding.root
        (activity?.applicationContext as ListComponentProvider).provideListComponent().inject(this)

        viewModel.getPokemons().observe(requireActivity(), { pokemons ->
            Log.e( "message", pokemons.toString())
            val adapter = PokemonAdapter()
            binding.recyclerPokemons.adapter = adapter
            adapter.pokemons = pokemons.results.toList()
        })
        return v
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

sealed class ListViewState
object ListSuccess : ListViewState()
object ListError : ListViewState()