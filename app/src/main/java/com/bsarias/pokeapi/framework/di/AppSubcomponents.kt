package com.bsarias.pokeapi.framework.di

import com.bsarias.pokeapi.list.framework.di.ListComponent
import dagger.Module

@Module(
    subcomponents = [
        ListComponent::class
    ]
)
class AppSubcomponents