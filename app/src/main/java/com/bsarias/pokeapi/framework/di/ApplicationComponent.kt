package com.bsarias.pokeapi.framework.di

import com.bsarias.pokeapi.MainActivity
import com.bsarias.pokeapi.list.framework.di.ListComponent
import dagger.Component
import dagger.Subcomponent
import javax.inject.Singleton

@Singleton
@Component(modules = [AppSubcomponents::class])
interface ApplicationComponent {

    fun inject(activity: MainActivity)
    fun listComponent(): ListComponent.Factory
}