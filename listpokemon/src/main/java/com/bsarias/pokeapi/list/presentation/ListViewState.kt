package com.bsarias.pokeapi.list.presentation

import com.bsarias.pokeapi.core.domain.ListPokemon

sealed class ListViewState {
    object Loading : ListViewState()
    data class Success(val listPokemon: ListPokemon) : ListViewState()
    data class Error(val error: String) : ListViewState()
}
