package com.bsarias.pokeapi.list.framework.network

import com.bsarias.pokeapi.core.domain.ListPokemon
import com.bsarias.pokeapi.list.data.source.ListRemoteDataSource

class ListRemoteDataSourceImpl(private val listPokemonService: ListPokemonService) :
    ListRemoteDataSource {
    override suspend fun doSearch(offset: Int, limit: Int): ListPokemon {
        return try {
            listPokemonService.getListPokemonAsync(offset, limit).mapToDomain()
        } catch (e: Exception) {
            throw Exception(e.message!!)
        }
    }
}