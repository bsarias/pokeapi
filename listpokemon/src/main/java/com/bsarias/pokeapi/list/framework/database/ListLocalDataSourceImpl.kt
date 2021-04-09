package com.bsarias.pokeapi.list.framework.database

import com.bsarias.pokeapi.core.domain.ListPokemon
import com.bsarias.pokeapi.list.data.source.ListLocalDataSource
import java.util.*
import kotlin.collections.HashSet

class ListLocalDataSourceImpl: ListLocalDataSource {
    override fun getStringSet(key: String): Set<String> {
        val set = mutableSetOf<String>()
        /*set.add("bulbasaur")
        set.add("charmander")
        set.add("mewtwo")*/
        return set
    }

    override fun saveStringSet(key: String, value: Set<String>) {

    }

    override fun delete(key: String) {
        TODO("Not yet implemented")
    }
}