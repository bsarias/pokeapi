package com.bsarias.pokeapi.details.data.source

import com.bsarias.pokeapi.details.domain.Pokemon


interface RemoteDataSource {
    suspend fun getPokemonByName(name: String): Pokemon
}