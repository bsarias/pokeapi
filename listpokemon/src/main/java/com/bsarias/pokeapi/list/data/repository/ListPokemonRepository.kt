package com.bsarias.pokeapi.list.data.repository

import com.bsarias.pokeapi.list.data.source.LocalDataSource
import com.bsarias.pokeapi.list.data.source.RemoteDataSource
import com.bsarias.pokeapi.list.domain.ListPokemon

class ListPokemonRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) {
    suspend fun doSearch(offset: Int, limit: Int, key: String): ListPokemon {
        lateinit var list: ListPokemon
        var data = localDataSource.getStringSet(key)

        if (data.isNullOrEmpty()) {
            list = remoteDataSource.doSearch(offset, limit)
            localDataSource.saveStringSet(key, list.results)
        } else {
            list = ListPokemon(data)
        }
        return list
    }
}