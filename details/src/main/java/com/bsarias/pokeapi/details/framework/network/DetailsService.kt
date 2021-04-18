package com.bsarias.pokeapi.details.framework.network

import com.bsarias.pokeapi.core.framework.network.models.PokemonAPI
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailsService {

    @GET(DETAILS_POKEMON)
    suspend fun getPokemonByName(
        @Path("pokemonName") pokemonName: String,
    ): PokemonAPI
}

private const val DETAILS_POKEMON = "pokemon/{pokemonName}"