package com.bsarias.pokeapi.details.framework.network

import com.bsarias.pokeapi.core.domain.Pokemon
import com.bsarias.pokeapi.details.data.source.DetailsRemoteDataSource

class DetailsRemoteDataSourceImpl(private val detailsService: DetailsService) :
    DetailsRemoteDataSource {
    override suspend fun getPokemonByName(name: String): Pokemon {
        return try {
            detailsService.getPokemonByName(name).mapToDomain()
        } catch (e: Exception) {
            throw Exception(e.message!!)
        }
    }
}