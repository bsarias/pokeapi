package com.bsarias.pokeapi.list.framework.di

import com.bsarias.pokeapi.core.framework.storage.MySharedPreferences
import com.bsarias.pokeapi.list.data.repository.ListPokemonRepository
import com.bsarias.pokeapi.list.data.repository.ListPokemonRepositoryImpl
import com.bsarias.pokeapi.list.data.source.ListLocalDataSource
import com.bsarias.pokeapi.list.data.source.ListRemoteDataSource
import com.bsarias.pokeapi.list.framework.local.ListLocalDataSourceImpl
import com.bsarias.pokeapi.list.framework.network.ListPokemonService
import com.bsarias.pokeapi.list.framework.network.ListRemoteDataSourceImpl
import com.bsarias.pokeapi.list.usecases.GetListPokemons
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ListModule {

    @Provides
    @ListScope
    fun providesGetListUseCase(listPokemonRepository: ListPokemonRepository): GetListPokemons =
        GetListPokemons(listPokemonRepository)

    @Provides
    @ListScope
    fun providesListPokemonRepository(
        localDataSource: ListLocalDataSource,
        remoteDataSource: ListRemoteDataSource,
    ): ListPokemonRepository =
        ListPokemonRepositoryImpl(localDataSource, remoteDataSource)

    @Provides
    @ListScope
    fun providesLocalDataSource(mySharedPreferences: MySharedPreferences): ListLocalDataSource =
        ListLocalDataSourceImpl(mySharedPreferences)

    @Provides
    @ListScope
    fun providesRemoteDataSource(listPokemonService: ListPokemonService): ListRemoteDataSource =
        ListRemoteDataSourceImpl(listPokemonService)

    @Provides
    @ListScope
    fun providesListService(retrofit: Retrofit): ListPokemonService =
        retrofit.create(ListPokemonService::class.java)
}