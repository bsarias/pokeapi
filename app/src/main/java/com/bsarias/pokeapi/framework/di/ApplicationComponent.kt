package com.bsarias.pokeapi.framework.di

import android.content.Context
import com.bsarias.pokeapi.MainActivity
import com.bsarias.pokeapi.core.framework.di.NetworkModule
import com.bsarias.pokeapi.core.framework.di.SharedPreferencesModule
import com.bsarias.pokeapi.list.framework.di.ListComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppSubcomponents::class, NetworkModule::class, SharedPreferencesModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

    fun inject(activity: MainActivity)
    fun listComponent(): ListComponent.Factory
}