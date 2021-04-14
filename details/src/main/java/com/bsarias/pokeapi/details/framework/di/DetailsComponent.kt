package com.bsarias.pokeapi.details.framework.di

import com.bsarias.pokeapi.details.presentation.DetailsFragment
import dagger.Subcomponent

@DetailsScope
@Subcomponent(modules = [DetailsModule::class])
interface DetailsComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): DetailsComponent
    }

    fun inject(fragment: DetailsFragment)
}