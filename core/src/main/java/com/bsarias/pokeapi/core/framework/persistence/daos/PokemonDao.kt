package com.bsarias.pokeapi.core.framework.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bsarias.pokeapi.core.framework.persistence.entities.PokemonDB

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePokemon(pokemonDB: PokemonDB)

    @Query("SELECT * FROM PokemonDB WHERE name = :pokemonName")
    fun getPokemonByName(pokemonName: String): PokemonDB

    @Query("SELECT COUNT(id) FROM PokemonDB WHERE name = :pokemonName")
    fun countPokemonByName(pokemonName: String): Int
}

