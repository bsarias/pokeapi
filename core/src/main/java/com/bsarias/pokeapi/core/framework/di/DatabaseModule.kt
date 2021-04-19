package com.bsarias.pokeapi.core.framework.di

import android.content.Context
import androidx.room.Room
import com.bsarias.pokeapi.core.framework.persistence.PokemonDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {
    @Provides
    fun providePokemonDatabase(context: Context): PokemonDatabase {
        return Room.databaseBuilder(context, PokemonDatabase::class.java, POKEMON_DATABASE_NAME)
            .build()
    }
}

private const val POKEMON_DATABASE_NAME = "pokemon-db"