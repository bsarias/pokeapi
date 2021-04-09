package com.bsarias.pokeapi.details.data.source

import com.bsarias.pokeapi.core.domain.Pokemon


interface RemoteDataSource {
    suspend fun getPokemonByName(name: String): com.bsarias.pokeapi.core.domain.Pokemon
}