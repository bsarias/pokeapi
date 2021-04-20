package com.bsarias.pokeapi

import androidx.test.espresso.Espresso

open class Page {
    companion object {
        inline fun <reified T : Page> on(): T {
            return Page().on()
        }
    }

    inline fun <reified T : Page> on(): T {
        val page = T::class.constructors.first().call()
        page.verify()
        return page
    }

    open fun verify(): Page {
        return this
    }

    fun back(): Page {
        Espresso.pressBack()
        return this
    }

    fun wait(seconds: Long): Page {
        Thread.sleep(seconds * 1000)
        return this
    }
}