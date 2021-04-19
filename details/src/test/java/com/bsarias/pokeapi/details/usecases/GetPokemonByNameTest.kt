package com.bsarias.pokeapi.details.usecases

import com.bsarias.pokeapi.core.domain.ListPokemon
import com.bsarias.pokeapi.core.domain.Pokemon
import com.bsarias.pokeapi.details.data.repository.DetailsRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetPokemonByNameTest {

    private val detailsRepository = mockk<DetailsRepository>(relaxed = true)
    private lateinit var getPokemonByName: GetPokemonByName

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
        getPokemonByName = GetPokemonByName(detailsRepository)
    }

    @Test
    fun `when invoke is called`(){
        coEvery {
            detailsRepository.getPokemonByName(pokemonName)
        } answers {
            flow {
                emit(pokemon)
            }
        }

        runBlocking {
            getPokemonByName(pokemonName).collect {
                Assert.assertEquals(pokemon, it)
            }
        }

        coVerify(exactly = 1) {
            detailsRepository.getPokemonByName(pokemonName)
        }


    }
}