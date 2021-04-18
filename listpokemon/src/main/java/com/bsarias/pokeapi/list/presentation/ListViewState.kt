package com.bsarias.pokeapi.list.presentation

import com.bsarias.pokeapi.core.domain.ListPokemon

sealed class ListViewState {
    object Loading : ListViewState()
    class Success(val listPokemon: ListPokemon) : ListViewState()
    class Error(val error: String) : ListViewState()
}
