package com.bsarias.pokeapi.list.framework.local

import com.bsarias.pokeapi.core.framework.storage.MySharedPreferences
import com.bsarias.pokeapi.list.data.source.ListLocalDataSource

class ListLocalDataSourceImpl(private val mySharedPreferences: MySharedPreferences) :
    ListLocalDataSource {
    override fun getListPokemon(key: String): List<String> {
        return mySharedPreferences.getStringSet(key)
    }

    override fun saveListPokemon(key: String, value: List<String>) {
        mySharedPreferences.saveStringSet(key, value)
    }

    override fun deleteListPokemon(key: String) {
        mySharedPreferences.delete(key)
    }
}