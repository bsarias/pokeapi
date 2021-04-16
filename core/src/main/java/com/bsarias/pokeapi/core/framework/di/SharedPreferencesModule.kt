package com.bsarias.pokeapi.core.framework.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides

@Module
class SharedPreferencesModule {

    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE)

}

private const val SHARED_PREFERENCES_FILE = "DATA"