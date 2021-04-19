package com.bsarias.pokeapi.details.data

import com.bsarias.pokeapi.core.domain.Pokemon
import com.bsarias.pokeapi.details.data.repository.DetailsRepository
import com.bsarias.pokeapi.details.data.repository.DetailsRepositoryImpl
import com.bsarias.pokeapi.details.data.source.DetailsPersistenceDataSource
import com.bsarias.pokeapi.details.data.source.DetailsRemoteDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DetailsRepositoryTest {

    private val detailsPersistenceDataSource = mockk<DetailsPersistenceDataSource>(relaxed = true)
    private val detailsRemoteDataSource = mockk<DetailsRemoteDataSource>(relaxed = true)
    private lateinit var detailsRepository: DetailsRepository

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
    fun init() {
        detailsRepository =
            DetailsRepositoryImpl(detailsPersistenceDataSource, detailsRemoteDataSource)
    }

    @Test
    fun `when the pokemon is stored locally`() {
        coEvery {
            detailsPersistenceDataSource.pokemonAlreadyExists(pokemonName)
        } answers {
            true
        }

        coEvery {
            detailsPersistenceDataSource.getPokemonByName(pokemonName)
        } answers {
            pokemon
        }

        runBlocking {
            detailsRepository.getPokemonByName(pokemonName).collect {
                Assert.assertEquals(pokemon, it)
            }
        }

        coVerify(exactly = 1) {
            detailsPersistenceDataSource.pokemonAlreadyExists(pokemonName)
            detailsPersistenceDataSource.getPokemonByName(pokemonName)
        }

        coVerify(exactly = 0) {
            detailsRemoteDataSource.getPokemonByName(pokemonName)
            detailsPersistenceDataSource.savePokemon(pokemon)
        }
    }

    @Test
    fun `when the pokemon is not stored locally`(){
        coEvery {
            detailsPersistenceDataSource.pokemonAlreadyExists(pokemonName)
        } answers {
            false
        }
        coEvery {
            detailsRemoteDataSource.getPokemonByName(pokemonName)
        } answers {
            pokemon
        }
        runBlocking {
            detailsRepository.getPokemonByName(pokemonName).collect {
                Assert.assertEquals(pokemon, it)
            }
        }
        coVerify(exactly = 0) {
            detailsPersistenceDataSource.getPokemonByName(pokemonName)
        }

        coVerify(exactly = 1) {
            detailsPersistenceDataSource.pokemonAlreadyExists(pokemonName)
            detailsRemoteDataSource.getPokemonByName(pokemonName)
            detailsPersistenceDataSource.savePokemon(pokemon)
        }
    }
}