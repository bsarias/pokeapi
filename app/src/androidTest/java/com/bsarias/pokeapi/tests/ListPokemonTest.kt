package com.bsarias.pokeapi.tests


import androidx.test.filters.LargeTest
import androidx.test.filters.SmallTest
import com.bsarias.pokeapi.Page.Companion.on
import com.bsarias.pokeapi.screens.ListScreen
import com.bsarias.pokeapi.screens.MainScreen
import org.junit.Test

@LargeTest
class ListPokemonTest {

    @Test
    @SmallTest
    fun verifyTitle() {
        on<MainScreen>()
            .start()
            .on<ListScreen>()
            .verifyTitle("Pokédex - Generation I")
    }

    @Test
    @SmallTest
    fun verifyRecyclerView() {
        on<MainScreen>()
            .start()
            .on<ListScreen>()
            .verifyCardIsShownByPokemonId("#001")
            .verifyCardIsShownByPokemonName("IVYSAUR")
    }

    @Test
    @SmallTest
    fun scrollRecyclerView() {
        on<MainScreen>()
            .start()
            .on<ListScreen>()
            .scrollList(149)
            .wait(1)
            .on<ListScreen>()
            .scrollList(15)
    }

    @Test
    @SmallTest
    fun verifyCardInAnyPosition() {
        on<MainScreen>()
            .start()
            .on<ListScreen>()
            .scrollList(149)
            .verifyCardIsShownByPokemonId("#149")
    }

    @Test
    @SmallTest
    fun onClickRecyclerView() {
        on<MainScreen>()
            .start()
            .on<ListScreen>()
            .scrollList(149)
            .onClickList(149)
    }


}
