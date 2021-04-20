package com.bsarias.pokeapi.screens

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import com.bsarias.pokeapi.Page
import com.bsarias.pokeapi.R
import org.hamcrest.Matchers

class ListScreen : Page() {


    fun verifyTitle(text: String): ListScreen {
        onView(withId(R.id.pokedex_title))
            .check(matches(withText(text)))
        return this
    }

    fun verifyCardIsShownByPokemonId(id: String): ListScreen {
        val textView = onView(
            Matchers.allOf(
                withId(R.id.pokemon_id), withText(id),
                withParent(withParent(withId(R.id.card))),
                isDisplayed()
            )
        )
        textView.check(matches(withText(id)))
        return this
    }

    fun verifyCardIsShownByPokemonName(name: String): ListScreen {
        val textView = onView(
            Matchers.allOf(
                withId(R.id.pokemon_name), withText(name),
                withParent(withParent(withId(R.id.card))),
                isDisplayed()
            )
        )
        textView.check(matches(withText(name)))
        return this
    }

    fun scrollList(position: Int): ListScreen {
        onView(withId(R.id.recycler_pokemons))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(position, scrollTo()))
        return this
    }

    fun onClickList(position: Int): ListScreen {
        onView(withId(R.id.recycler_pokemons))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(position, click()))
        return this
    }
}