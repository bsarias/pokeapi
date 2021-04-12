package com.bsarias.pokeapi.list.framework.di

import com.bsarias.pokeapi.list.data.repository.ListPokemonRepository
import com.bsarias.pokeapi.list.data.repository.ListPokemonRepositoryImpl
import com.bsarias.pokeapi.list.data.source.ListLocalDataSource
import com.bsarias.pokeapi.list.data.source.ListRemoteDataSource
import com.bsarias.pokeapi.list.framework.database.ListLocalDataSourceImpl
import com.bsarias.pokeapi.list.framework.network.ListRemoteDataSourceImpl
import com.bsarias.pokeapi.list.usecases.GetListPokemons
import dagger.Module
import dagger.Provides

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
    fun providesLocalDataSource(): ListLocalDataSource = ListLocalDataSourceImpl()

    @Provides
    @ListScope
    fun providesRemoteDataSource(): ListRemoteDataSource = ListRemoteDataSourceImpl()
}