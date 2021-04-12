package com.bsarias.pokeapi.list.data.repository

import com.bsarias.pokeapi.core.domain.ListPokemon
import kotlinx.coroutines.flow.Flow

interface ListPokemonRepository {
    suspend fun listPokemons(offset: Int, limit: Int, key: String): Flow<ListPokemon>
}