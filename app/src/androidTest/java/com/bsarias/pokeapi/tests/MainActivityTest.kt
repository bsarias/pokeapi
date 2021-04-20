package com.bsarias.pokeapi.tests

import androidx.test.filters.LargeTest
import androidx.test.filters.MediumTest
import com.bsarias.pokeapi.Page
import com.bsarias.pokeapi.screens.DetailsScreen
import com.bsarias.pokeapi.screens.ListScreen
import com.bsarias.pokeapi.screens.MainScreen
import org.junit.Test

@LargeTest
class MainActivityTest {

    @Test
    @MediumTest
    fun checkCompleteFlow(){
        Page.on<MainScreen>()
            .start()
            .on<ListScreen>()
            .verifyTitle("Pokédex - Generation I")
            .scrollList(149)
            .verifyCardIsShownByPokemonId("#150")
            .verifyCardIsShownByPokemonName("MEWTWO")
            .on<DetailsScreen>()
            .openDetailScreenAtPosition(15)
            .verifyPokemonName("PIDGEY")
            .verifyPokemonId("#016")
            .verifyPokemonWeight("18 HECTOGRAMS")
            .verifyPokemonHeight("3 DECIMETERS")
            .verifyPokemonTypes("NORMAL, FLYING")
            .back()
            .wait(3)
            .on<ListScreen>()
            .verifyTitle("Pokédex - Generation I")
            .scrollList(150)
            .verifyCardIsShownByPokemonId("#151")
            .verifyCardIsShownByPokemonName("MEW")
    }

}