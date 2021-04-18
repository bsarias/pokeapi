package com.bsarias.pokeapi.framework.di

import android.content.Context
import com.bsarias.pokeapi.MainActivity
import com.bsarias.pokeapi.core.framework.di.DatabaseModule
import com.bsarias.pokeapi.core.framework.di.NetworkModule
import com.bsarias.pokeapi.details.framework.di.DetailsComponent
import com.bsarias.pokeapi.list.framework.di.ListComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppSubcomponents::class, NetworkModule::class, DatabaseModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

    fun inject(activity: MainActivity)
    fun listComponent(): ListComponent.Factory
    fun detailsComponent(): DetailsComponent.Factory
}