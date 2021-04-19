package com.bsarias.pokeapi.list.framework.network

import com.bsarias.pokeapi.core.framework.network.models.SearchAPI
import retrofit2.http.GET
import retrofit2.http.Query

interface ListPokemonService {

    @GET(LIST_POKEMON)
    suspend fun getListPokemonAsync(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): SearchAPI
}

private const val LIST_POKEMON = "pokemon"