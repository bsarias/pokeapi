package com.bsarias.pokeapi.list.data.source

import com.bsarias.pokeapi.list.domain.ListPokemon

interface RemoteDataSource {
    suspend fun doSearch(offset: Int, limit: Int): ListPokemon
}