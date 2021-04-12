package com.bsarias.pokeapi.list.data.repository

import com.bsarias.pokeapi.core.domain.ListPokemon
import com.bsarias.pokeapi.list.data.source.ListLocalDataSource
import com.bsarias.pokeapi.list.data.source.ListRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ListPokemonRepositoryImpl(
    private val listLocalDataSource: ListLocalDataSource,
    private val listRemoteDataSource: ListRemoteDataSource,
) : ListPokemonRepository {

    override suspend fun listPokemons(offset: Int, limit: Int, key: String): Flow<ListPokemon> {
        return flow {
            emit(getPokemons(offset, limit, key))
        }
    }

    private suspend fun getPokemons(offset: Int, limit: Int, key: String): ListPokemon {
        lateinit var list: ListPokemon
        val data = listLocalDataSource.getListPokemon(key)
        if (data.isNullOrEmpty()) {
            list = listRemoteDataSource.doSearch(offset, limit)
            listLocalDataSource.saveListPokemon(key, list.results)
        } else {
            list = ListPokemon(data)
        }
        return list
    }
}