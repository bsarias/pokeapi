package com.bsarias.pokeapi.list.data.repository

import com.bsarias.pokeapi.core.domain.ListPokemon

interface ListPokemonRepository {
    suspend fun listPokemons(offset: Int, limit: Int, key: String): ListPokemon
}