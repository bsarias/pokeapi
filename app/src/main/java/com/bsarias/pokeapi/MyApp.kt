package com.bsarias.pokeapi

import android.app.Application
import com.bsarias.pokeapi.details.framework.di.DetailsComponent
import com.bsarias.pokeapi.details.framework.di.DetailsComponentProvider
import com.bsarias.pokeapi.framework.di.ApplicationComponent
import com.bsarias.pokeapi.framework.di.DaggerApplicationComponent
import com.bsarias.pokeapi.list.framework.di.ListComponent
import com.bsarias.pokeapi.list.framework.di.ListComponentProvider

open class MyApp : Application(), ListComponentProvider, DetailsComponentProvider {
    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.factory().create(this)
    }

    override fun provideListComponent(): ListComponent {
        return appComponent.listComponent().create()
    }

    override fun provideDetailsComponent(): DetailsComponent {
        return appComponent.detailsComponent().create()
    }
}