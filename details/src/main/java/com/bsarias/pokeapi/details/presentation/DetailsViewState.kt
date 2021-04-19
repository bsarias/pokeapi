package com.bsarias.pokeapi.details.presentation

import com.bsarias.pokeapi.core.domain.Pokemon

sealed class DetailsViewState {
    object Loading : DetailsViewState()
    data class Success(val pokemon: Pokemon) : DetailsViewState()
    data class Error(val error: String) : DetailsViewState()
}
