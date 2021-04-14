package com.bsarias.pokeapi.framework.di

import com.bsarias.pokeapi.details.framework.di.DetailsComponent
import com.bsarias.pokeapi.list.framework.di.ListComponent
import dagger.Module

@Module(subcomponents = [ListComponent::class, DetailsComponent::class])
class AppSubcomponents