package com.bsarias.pokeapi.list.framework.di

import com.bsarias.pokeapi.list.presentation.ListFragment
import dagger.Component
import dagger.Subcomponent

@ListScope
@Subcomponent(modules = [ListModule::class])
interface ListComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ListComponent
    }
    fun inject(fragment: ListFragment)
}