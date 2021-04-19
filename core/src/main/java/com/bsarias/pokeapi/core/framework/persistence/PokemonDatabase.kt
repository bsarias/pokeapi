package com.bsarias.pokeapi.core.framework.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bsarias.pokeapi.core.framework.persistence.daos.PokemonDao
import com.bsarias.pokeapi.core.framework.persistence.entities.PokemonDB

@Database(entities = [PokemonDB::class], version = 1, exportSchema = false)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}