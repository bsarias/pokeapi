package com.bsarias.pokeapi.list.framework.network

import com.bsarias.pokeapi.core.domain.ListPokemon
import com.bsarias.pokeapi.list.data.source.ListRemoteDataSource
import java.util.*
import kotlin.collections.ArrayList

class ListRemoteDataSourceImpl : ListRemoteDataSource {
    override suspend fun doSearch(offset: Int, limit: Int): ListPokemon {
        val set = mutableSetOf<String>()
        set.add("bulbasaur")
        set.add("charmander")
        set.add("squirtle")
        return ListPokemon(set)
    }
}