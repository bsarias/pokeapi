package com.bsarias.pokeapi.data.repository

interface LocalRepository {
    fun getStringSet(key: String): Set<String>
    fun saveStringSet(key: String, value: Set<String>)
    fun delete(key: String)
}