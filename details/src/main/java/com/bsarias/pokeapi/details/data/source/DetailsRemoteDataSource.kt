package com.bsarias.pokeapi.details.data.source

import com.bsarias.pokeapi.core.domain.Pokemon

interface DetailsRemoteDataSource {
    suspend fun getPokemonByName(name: String): Pokemon
}