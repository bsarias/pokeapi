package com.bsarias.pokeapi.details.data.source

import com.bsarias.pokeapi.details.domain.Pokemon


interface PersistenceDataSource {
    suspend fun getPokemonByName(name: String): Pokemon
    suspend fun pokemonAlreadyExists(name: String): Boolean
    suspend fun savePokemon(pokemon: Pokemon)
}