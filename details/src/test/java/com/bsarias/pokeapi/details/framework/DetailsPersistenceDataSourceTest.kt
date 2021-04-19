package com.bsarias.pokeapi.details.framework

import com.bsarias.pokeapi.core.domain.Pokemon
import com.bsarias.pokeapi.core.framework.persistence.daos.PokemonDao
import com.bsarias.pokeapi.core.framework.persistence.entities.PokemonDB
import com.bsarias.pokeapi.details.data.source.DetailsPersistenceDataSource
import com.bsarias.pokeapi.details.framework.persistence.DetailsPersistenceDataSourceImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DetailsPersistenceDataSourceTest {

    private val pokemonDao = mockk<PokemonDao>(relaxed = true)
    private lateinit var detailsPersistenceDataSource: DetailsPersistenceDataSource

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
        detailsPersistenceDataSource = DetailsPersistenceDataSourceImpl(pokemonDao)
    }

    @Test
    fun `should get pokemon by name stored locally`() {
        coEvery {
            pokemonDao.getPokemonByName(pokemonName).mapToDomain()
        } answers {
            pokemon
        }

        runBlocking {
            val pokemonLocal = detailsPersistenceDataSource.getPokemonByName(pokemonName)
            Assert.assertEquals(pokemon, pokemonLocal)
        }

        coVerify(exactly = 1) {
            pokemonDao.getPokemonByName(pokemonName)
        }
    }

    @Test
    fun `should response pokemon already exist`(){
        coEvery {
            pokemonDao.countPokemonByName(pokemonName)
        } answers {
            1
        }

        runBlocking {
            val response = detailsPersistenceDataSource.pokemonAlreadyExists(pokemonName)
            Assert.assertEquals(true, response)
        }

        coVerify(exactly = 1) {
            pokemonDao.countPokemonByName(pokemonName)
        }
    }

    @Test
    fun `should response pokemon does not exist`(){
        coEvery {
            pokemonDao.countPokemonByName(pokemonName)
        } answers {
            0
        }

        runBlocking {
            val response = detailsPersistenceDataSource.pokemonAlreadyExists(pokemonName)
            Assert.assertEquals(false, response)
        }

        coVerify(exactly = 1) {
            pokemonDao.countPokemonByName(pokemonName)
        }
    }

    @Test
    fun `should save a pokemon locally`(){
        runBlocking {
            detailsPersistenceDataSource.savePokemon(pokemon)
        }

        verify(exactly = 1) {
            pokemonDao.savePokemon(PokemonDB(pokemon))
        }
    }

}