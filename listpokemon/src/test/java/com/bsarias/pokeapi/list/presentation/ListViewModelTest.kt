package com.bsarias.pokeapi.list.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.bsarias.pokeapi.core.domain.ListPokemon
import com.bsarias.pokeapi.list.CoroutineTestRule
import com.bsarias.pokeapi.list.usecases.GetListPokemons
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ListViewModelTest {

    @get: Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    @get: Rule

    var coroutineTestRule = CoroutineTestRule()

    private val getListPokemons = mockk<GetListPokemons>(relaxed = true)
    private val observer = mockk<Observer<ListViewState>>(relaxed = true)
    private lateinit var listViewModel: ListViewModel

    private val results = listOf("bulbasaur", "squirtle", "charmander")
    private val key = "POKEMON"
    private val offset = 0
    private val limit = 100

    @Before
    fun init() {
        listViewModel = ListViewModel(getListPokemons, coroutineTestRule.testDispatcher)
    }


    @Test
    fun `when data get successfully`() {

        listViewModel.getPokemons().observeForever(observer)

        coEvery {
            getListPokemons(offset, limit, key)
        } answers {
            flow {
                emit(ListPokemon(results))
            }
        }

        coVerify(exactly = 1) {
            getListPokemons(offset, limit, key)
            observer.onChanged(ListViewState.Success(ListPokemon(results)))
        }

        listViewModel.getPokemons().removeObserver(observer)

    }


}