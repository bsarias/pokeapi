package com.bsarias.pokeapi.list.data.source

interface ListLocalDataSource {
    fun getStringSet(key: String): Set<String>
    fun saveStringSet(key: String, value: Set<String>)
    fun delete(key: String)
}