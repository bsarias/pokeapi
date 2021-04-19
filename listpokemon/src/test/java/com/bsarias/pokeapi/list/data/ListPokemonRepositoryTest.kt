package com.bsarias.pokeapi.list.data

import com.bsarias.pokeapi.list.data.repository.ListPokemonRepository
import com.bsarias.pokeapi.list.data.repository.ListPokemonRepositoryImpl
import com.bsarias.pokeapi.list.data.source.ListLocalDataSource
import com.bsarias.pokeapi.list.data.source.ListRemoteDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class ListPokemonRepositoryTest {

    private val listLocalDataSource = mockk<ListLocalDataSource>(relaxed = true)
    private val listRemoteDataSource = mockk<ListRemoteDataSource>(relaxed = true)
    private lateinit var listPokemonRepository: ListPokemonRepository

    private val results = listOf("bulbasaur", "squirtle", "charmander")
    private val key = "POKEMON"
    private val offset = 0
    private val limit = 100


    @Before
    fun init() {
        listPokemonRepository = ListPokemonRepositoryImpl(listLocalDataSource, listRemoteDataSource)
    }


    @Test
    fun `when there is a pokemon list stored locally`() {
        coEvery {
            listLocalDataSource.getListPokemon(key)
        } answers {
            results
        }

        runBlocking {
            listPokemonRepository.listPokemons(offset, limit, key)
                .collect {
                    Assert.assertEquals(results, it.results)
                }
        }

        coVerify(exactly = 1) {
            listLocalDataSource.getListPokemon(key)
        }

        coVerify(exactly = 0) {
            listRemoteDataSource.doSearch(offset, limit)
            listLocalDataSource.saveListPokemon(key, results)
        }
    }

    @Test
    fun `when there is no a pokemon list stored locally`() {
        coEvery {
            listLocalDataSource.getListPokemon(key)
        } answers {
            listOf()
        }

        coEvery {
            listRemoteDataSource.doSearch(offset, limit).results
        } answers {
            results
        }

        runBlocking {
            listPokemonRepository.listPokemons(offset, limit, key)
                .collect {
                    Assert.assertEquals(results, it.results)
                }
        }

        coVerify(exactly = 1) {
            listRemoteDataSource.doSearch(offset, limit)
            listLocalDataSource.saveListPokemon(key, results)
        }

    }

}