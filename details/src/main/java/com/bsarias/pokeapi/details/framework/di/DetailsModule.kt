package com.bsarias.pokeapi.details.framework.di

import com.bsarias.pokeapi.core.framework.persistence.PokemonDatabase
import com.bsarias.pokeapi.details.data.repository.DetailsRepository
import com.bsarias.pokeapi.details.data.repository.DetailsRepositoryImpl
import com.bsarias.pokeapi.details.data.source.DetailsPersistenceDataSource
import com.bsarias.pokeapi.details.data.source.DetailsRemoteDataSource
import com.bsarias.pokeapi.details.framework.network.DetailsRemoteDataSourceImpl
import com.bsarias.pokeapi.details.framework.network.DetailsService
import com.bsarias.pokeapi.details.framework.persistence.DetailsPersistenceDataSourceImpl
import com.bsarias.pokeapi.details.usecases.GetPokemonByName
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class DetailsModule {

    @Provides
    @DetailsScope
    fun provideGetPokemonUseCase(detailsRepository: DetailsRepository): GetPokemonByName =
        GetPokemonByName(detailsRepository)

    @Provides
    @DetailsScope
    fun provideDetailRepository(
        persistenceDataSource: DetailsPersistenceDataSource,
        remoteDataSource: DetailsRemoteDataSource
    ): DetailsRepository = DetailsRepositoryImpl(persistenceDataSource, remoteDataSource)


    @Provides
    @DetailsScope
    fun providePersistenceDataSource(database: PokemonDatabase): DetailsPersistenceDataSource =
        DetailsPersistenceDataSourceImpl(database.pokemonDao())

    @Provides
    @DetailsScope
    fun provideRemoteDataSource(detailsService: DetailsService): DetailsRemoteDataSource =
        DetailsRemoteDataSourceImpl(detailsService)

    @Provides
    @DetailsScope
    fun provideDetailsService(retrofit: Retrofit): DetailsService =
        retrofit.create(DetailsService::class.java)

}