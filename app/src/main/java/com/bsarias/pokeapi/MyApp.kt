package com.bsarias.pokeapi

import android.app.Application
import com.bsarias.pokeapi.framework.di.ApplicationComponent
import com.bsarias.pokeapi.framework.di.DaggerApplicationComponent
import com.bsarias.pokeapi.list.framework.di.ListComponent
import com.bsarias.pokeapi.list.framework.di.ListComponentProvider

open class MyApp : Application(), ListComponentProvider {
    val appComponent: ApplicationComponent = DaggerApplicationComponent.create()


    override fun provideListComponent(): ListComponent {
        return appComponent.listComponent().create()
    }
}