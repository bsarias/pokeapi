package com.bsarias.pokeapi.list.framework.network

import com.bsarias.pokeapi.core.domain.ListPokemon
import com.bsarias.pokeapi.core.framework.network.mappers.ListPokemonMapper
import com.bsarias.pokeapi.list.data.source.ListRemoteDataSource

class ListRemoteDataSourceImpl(private val listPokemonService: ListPokemonService) :
    ListRemoteDataSource {
    override suspend fun doSearch(offset: Int, limit: Int): ListPokemon {
        val call = listPokemonService.getListPokemonAsync(offset, limit)
        val list = call.body()
        if (call.isSuccessful) {
            return ListPokemonMapper.map(list!!)
        } else {
            throw Exception(call.message())
        }
    }
}