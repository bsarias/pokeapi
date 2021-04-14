package com.bsarias.pokeapi.details.framework.persistence

import com.bsarias.pokeapi.core.domain.Pokemon
import com.bsarias.pokeapi.core.framework.persistence.daos.PokemonDao
import com.bsarias.pokeapi.core.framework.persistence.mappers.mapToEntity
import com.bsarias.pokeapi.details.data.source.DetailsPersistenceDataSource

class DetailsPersistenceDataSourceImpl(private val dao: PokemonDao) : DetailsPersistenceDataSource {
    override suspend fun getPokemonByName(name: String): Pokemon {
        return dao.getPokemonByName(name).mapToDomain()
    }

    override suspend fun pokemonAlreadyExists(name: String): Boolean {
        return dao.countPokemonByName(name) > 0
    }

    override suspend fun savePokemon(pokemon: Pokemon) {
        dao.savePokemon(pokemon.mapToEntity())
    }
}