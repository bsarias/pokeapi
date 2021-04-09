package com.bsarias.pokeapi.details.data.repository

import com.bsarias.pokeapi.core.domain.Pokemon
import com.bsarias.pokeapi.details.data.source.PersistenceDataSource
import com.bsarias.pokeapi.details.data.source.RemoteDataSource

class PokemonRepository(
    private val persistenceDataSource: PersistenceDataSource,
    private val remoteDataSource: RemoteDataSource,
) {

    suspend fun getPokemonByName(name: String): Pokemon {
        lateinit var pokemon: Pokemon
        if (persistenceDataSource.pokemonAlreadyExists(name)) {
            pokemon = persistenceDataSource.getPokemonByName(name)
        } else {
            pokemon = remoteDataSource.getPokemonByName(name)
            persistenceDataSource.savePokemon(pokemon)
        }
        return pokemon
    }
}