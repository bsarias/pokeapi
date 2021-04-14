package com.bsarias.pokeapi.list.data.source

interface ListLocalDataSource {
    fun getListPokemon(key: String): List<String>
    fun saveListPokemon(key: String, value: List<String>)
    fun deleteListPokemon(key: String)
}