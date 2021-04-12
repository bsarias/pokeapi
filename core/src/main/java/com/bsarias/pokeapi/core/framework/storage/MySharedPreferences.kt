package com.bsarias.pokeapi.core.framework.storage

import android.content.SharedPreferences
import javax.inject.Inject

class MySharedPreferences @Inject constructor(private val sharedPreferences: SharedPreferences) {
    fun getStringSet(key: String): List<String> {
        return sharedPreferences.getStringSet(key, setOf())?.toList() ?: listOf()
    }

    fun saveStringSet(key: String, value: List<String>) {
        sharedPreferences.edit().putStringSet(key, value.toSet()).apply()
    }

    fun delete(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }
}
