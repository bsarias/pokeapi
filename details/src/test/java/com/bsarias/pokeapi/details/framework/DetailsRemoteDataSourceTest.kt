package com.bsarias.pokeapi.details.framework

import android.accounts.NetworkErrorException
import com.bsarias.pokeapi.core.domain.ListPokemon
import com.bsarias.pokeapi.core.domain.Pokemon
import com.bsarias.pokeapi.details.data.source.DetailsRemoteDataSource
import com.bsarias.pokeapi.details.framework.network.DetailsRemoteDataSourceImpl
import com.bsarias.pokeapi.details.framework.network.DetailsService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.lang.Exception

class DetailsRemoteDataSourceTest {

    private val detailsService = mockk<DetailsService>(relaxed = true)
    private lateinit var detailsRemoteDataSource: DetailsRemoteDataSource

    private val pokemonName = "Bulbasaur"
    private val pokemon = Pokemon(
        id = 1,
        name = "Bulbasaur",
        types = "[grass]",
        height = 12,
        weight = 45,
        frontDefault = "",
        officialArtwork = "",
    )

    @Before
    fun init(){
        detailsRemoteDataSource = DetailsRemoteDataSourceImpl(detailsService)
    }

    @Test
    fun `when data is got successfully`() {
        coEvery {
            detailsService.getPokemonByName(pokemonName).mapToDomain()
        } answers {
            pokemon
        }

        runBlocking {
            val response = detailsRemoteDataSource.getPokemonByName(pokemonName)
            Assert.assertEquals(pokemon, response)
        }

        coVerify(exactly = 1) {
            detailsService.getPokemonByName(pokemonName)
        }

    }

    @Test(expected = Exception::class)
    fun `when is thrown an Exception`(){
        coEvery {
            detailsService.getPokemonByName(pokemonName)
        } answers {
            throw NetworkErrorException()
        }

        runBlocking {
            detailsRemoteDataSource.getPokemonByName(pokemonName)
        }

    }
}