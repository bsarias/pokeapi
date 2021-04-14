package com.bsarias.pokeapi.details.data.repository

import com.bsarias.pokeapi.core.domain.Pokemon
import kotlinx.coroutines.flow.Flow

interface DetailsRepository {
    fun getPokemonByName(pokemonName: String): Flow<Pokemon>
}