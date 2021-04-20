package com.bsarias.pokeapi.screens

import androidx.test.rule.ActivityTestRule
import com.bsarias.pokeapi.MainActivity
import com.bsarias.pokeapi.Page
import org.junit.Rule

class MainScreen : Page() {
    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)


    fun start(): MainScreen {
        mActivityTestRule.launchActivity(null)
        return this
    }
}