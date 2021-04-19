package com.bsarias.pokeapi.details.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.bsarias.pokeapi.core.domain.Pokemon
import com.bsarias.pokeapi.core.testutils.CoroutineTestRule
import com.bsarias.pokeapi.details.usecases.GetPokemonByName
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailsViewModelTest {

    @get: Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get: Rule
    var coroutineTestRule = CoroutineTestRule()

    private val getPokemonByName = mockk<GetPokemonByName>(relaxed = true)
    private val observer = mockk<Observer<DetailsViewState>>(relaxed = true)
    private lateinit var detailsViewModel: DetailsViewModel

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
        detailsViewModel = DetailsViewModel(getPokemonByName, coroutineTestRule.testDispatcher)
    }

    @Test
    fun `when data get successfully`() {
        coEvery {
            getPokemonByName(pokemonName)
        } answers {
            flow {
                emit(pokemon)
            }
        }

        detailsViewModel.getDetail(pokemonName).observeForever(observer)
        detailsViewModel.loadDetails()

        coVerify(exactly = 1) {
            getPokemonByName(pokemonName)
            observer.onChanged(DetailsViewState.Success(pokemon))
        }

        detailsViewModel.getDetail(pokemonName).removeObserver(observer)

    }
}