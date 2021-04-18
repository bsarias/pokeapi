package com.bsarias.pokeapi.core.framework.storage

import android.content.Context
import javax.inject.Inject

class MySharedPreferences @Inject constructor(private val context: Context) {

    fun getStringSet(key: String): List<String> {
        return context.getSharedPreferences(SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE)
            .getStringSet(key, setOf())?.toList() ?: listOf()
    }

    fun saveStringSet(key: String, value: List<String>) {
        context.getSharedPreferences(SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE).edit()
            .putStringSet(key, value.toSet()).apply()
    }

    fun delete(key: String) {
        context.getSharedPreferences(SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE).edit()
            .remove(key).apply()
    }
}

private const val SHARED_PREFERENCES_FILE = "DATA"