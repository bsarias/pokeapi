package com.bsarias.pokeapi.details.data.repository

import com.bsarias.pokeapi.core.domain.Pokemon
import com.bsarias.pokeapi.details.data.source.DetailsPersistenceDataSource
import com.bsarias.pokeapi.details.data.source.DetailsRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DetailsRepositoryImpl(
    private val detailsPersistenceDataSource: DetailsPersistenceDataSource,
    private val detailsRemoteDataSource: DetailsRemoteDataSource,
) : DetailsRepository {

    override fun getPokemonByName(pokemonName: String): Flow<Pokemon> {
        return flow {
            emit(findPokemon(pokemonName))
        }
    }

    private suspend fun findPokemon(name: String): Pokemon {
        lateinit var pokemon: Pokemon
        if (detailsPersistenceDataSource.pokemonAlreadyExists(name)) {
            pokemon = detailsPersistenceDataSource.getPokemonByName(name)
        } else {
            pokemon = detailsRemoteDataSource.getPokemonByName(name)
            detailsPersistenceDataSource.savePokemon(pokemon)
        }
        return pokemon
    }


}