package com.bsarias.pokeapi.list.framework.network

import com.bsarias.pokeapi.core.framework.network.models.SearchAPI
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ListPokemonService {

    @GET(LIST_POKEMON)
    suspend fun getListPokemonAsync(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): Response<SearchAPI>
}

private const val LIST_POKEMON = "pokemon"