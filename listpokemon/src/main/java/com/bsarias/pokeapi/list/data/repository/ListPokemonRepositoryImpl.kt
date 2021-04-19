package com.bsarias.pokeapi.list.data.repository

import android.util.Log
import com.bsarias.pokeapi.core.domain.ListPokemon
import com.bsarias.pokeapi.list.data.source.ListLocalDataSource
import com.bsarias.pokeapi.list.data.source.ListRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ListPokemonRepositoryImpl(
    private val listLocalDataSource: ListLocalDataSource,
    private val listRemoteDataSource: ListRemoteDataSource,
) : ListPokemonRepository {

    override fun listPokemons(offset: Int, limit: Int, key: String): Flow<ListPokemon> {
        return flow {
            emit(getPokemons(offset, limit, key))
        }
    }

    private suspend fun getPokemons(offset: Int, limit: Int, key: String): ListPokemon {
        lateinit var list: ListPokemon
        val data = listLocalDataSource.getListPokemon(key)
        if (data.isNullOrEmpty()) {
            list = listRemoteDataSource.doSearch(offset, limit)
            list.results =
                list.results.mapIndexed { index, s -> joinNumberToName(offset + index + 1, s) }
            listLocalDataSource.saveListPokemon(key, list.results)
        } else {
            list = ListPokemon(data)
        }
        return list
    }

    private fun joinNumberToName(offset: Int, name: String): String {
        val number = if (offset < 10) "00$offset" else if (offset < 100) "0$offset" else "$offset"
        return "$number-$name"
    }
}