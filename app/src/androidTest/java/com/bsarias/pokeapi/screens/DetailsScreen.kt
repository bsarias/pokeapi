package com.bsarias.pokeapi.screens

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.bsarias.pokeapi.Page
import com.bsarias.pokeapi.R

class DetailsScreen : Page() {


    fun openDetailScreenAtPosition(position: Int): DetailsScreen {
        onView(withId(R.id.recycler_pokemons))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(position, scrollTo()))
        onView(withId(R.id.recycler_pokemons))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(position, click()))
        return this
    }

    fun verifyPokemonName(name: String): DetailsScreen {
        onView(withId(R.id.pokemonName))
            .check(matches(withText(name)))
        return this
    }

    fun verifyPokemonId(id: String): DetailsScreen {
        onView(withId(R.id.pokemon_id))
            .check(matches(withText(id)))
        return this
    }

    fun verifyPokemonWeight(text: String): DetailsScreen {
        onView(withId(R.id.pokemonWeight))
            .check(matches(withText(text)))
        return this
    }

    fun verifyPokemonHeight(text: String): DetailsScreen {
        onView(withId(R.id.pokemonHeight))
            .check(matches(withText(text)))
        return this
    }

    fun verifyPokemonTypes(text: String): DetailsScreen {
        onView(withId(R.id.pokemonTypes))
            .check(matches(withText(text)))
        return this
    }

}