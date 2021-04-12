package com.bsarias.pokeapi.details.data.source

import com.bsarias.pokeapi.core.domain.Pokemon


interface PersistenceDataSource {
    suspend fun getPokemonByName(name: String): com.bsarias.pokeapi.core.domain.Pokemon
    suspend fun pokemonAlreadyExists(name: String): Boolean
    suspend fun savePokemon(pokemon: com.bsarias.pokeapi.core.domain.Pokemon)
}