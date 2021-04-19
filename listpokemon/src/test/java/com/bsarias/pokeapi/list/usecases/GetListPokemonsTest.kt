package com.bsarias.pokeapi.list.usecases

import com.bsarias.pokeapi.core.domain.ListPokemon
import com.bsarias.pokeapi.list.data.repository.ListPokemonRepository
import com.bsarias.pokeapi.list.usecases.GetListPokemons
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetListPokemonsTest {
    private val listPokemonRepository = mockk<ListPokemonRepository>(relaxed = true)
    private lateinit var getListPokemons: GetListPokemons

    private val results = listOf("bulbasaur", "squirtle", "charmander")
    private val key = "POKEMON"
    private val offset = 0
    private val limit = 100


    @Before
    fun init(){
        getListPokemons = GetListPokemons(listPokemonRepository)
    }

    @Test
    fun `when invoke is called`(){
        coEvery {
            listPokemonRepository.listPokemons(offset, limit, key)
        } answers {
            flow {
                emit(ListPokemon(results))
            }
        }

        runBlocking {
            getListPokemons(offset, limit, key).collect {
                Assert.assertEquals(results, it.results)
            }
        }

        coVerify(exactly = 1) {
            listPokemonRepository.listPokemons(offset, limit, key)
        }

    }


}