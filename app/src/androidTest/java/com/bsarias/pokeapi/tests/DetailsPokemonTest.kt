package com.bsarias.pokeapi.tests

import androidx.test.filters.LargeTest
import androidx.test.filters.SmallTest
import com.bsarias.pokeapi.Page
import com.bsarias.pokeapi.screens.DetailsScreen
import com.bsarias.pokeapi.screens.MainScreen
import org.junit.Test

@LargeTest
class DetailsPokemonTest {

    @Test
    @SmallTest
    fun openDetailsScreen(){
        Page.on<MainScreen>()
            .start()
            .on<DetailsScreen>()
            .openDetailScreenAtPosition(15)
    }

    @Test
    @SmallTest
    fun chechPokemonName(){
        Page.on<MainScreen>()
            .start()
            .on<DetailsScreen>()
            .openDetailScreenAtPosition(15)
            .verifyPokemonName("PIDGEY")
    }

    @Test
    @SmallTest
    fun chechPokemonId(){
        Page.on<MainScreen>()
            .start()
            .on<DetailsScreen>()
            .openDetailScreenAtPosition(15)
            .verifyPokemonId("#016")
    }

    @Test
    @SmallTest
    fun checkPokemonWeight(){
        Page.on<MainScreen>()
            .start()
            .on<DetailsScreen>()
            .openDetailScreenAtPosition(15)
            .verifyPokemonWeight("18 HECTOGRAMS")
    }

    @Test
    @SmallTest
    fun chechPokemonHeight(){
        Page.on<MainScreen>()
            .start()
            .on<DetailsScreen>()
            .openDetailScreenAtPosition(15)
            .verifyPokemonHeight("3 DECIMETERS")
    }

    @Test
    @SmallTest
    fun chechPokemonTypes(){
        Page.on<MainScreen>()
            .start()
            .on<DetailsScreen>()
            .openDetailScreenAtPosition(15)
            .verifyPokemonTypes("NORMAL, FLYING")
    }

    @Test
    @SmallTest
    fun checkAll(){
        Page.on<MainScreen>()
            .start()
            .on<DetailsScreen>()
            .openDetailScreenAtPosition(15)
            .verifyPokemonName("PIDGEY")
            .verifyPokemonId("#016")
            .verifyPokemonWeight("18 HECTOGRAMS")
            .verifyPokemonHeight("3 DECIMETERS")
            .verifyPokemonTypes("NORMAL, FLYING")
    }


}