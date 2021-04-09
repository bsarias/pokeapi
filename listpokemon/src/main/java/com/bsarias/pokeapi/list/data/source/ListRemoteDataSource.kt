package com.bsarias.pokeapi.list.data.source

import com.bsarias.pokeapi.core.domain.ListPokemon

interface ListRemoteDataSource {
    suspend fun doSearch(offset: Int, limit: Int): ListPokemon
}