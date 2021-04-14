package com.bsarias.pokeapi.core.framework.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .build()
    }

    @Provides
    fun provideRetrofit(
        httpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

}

private const val BASE_URL = "https://pokeapi.co/api/v2/"