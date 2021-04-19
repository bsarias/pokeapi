package com.bsarias.pokeapi.list.framework

import com.bsarias.pokeapi.core.framework.storage.MySharedPreferences
import com.bsarias.pokeapi.list.data.source.ListLocalDataSource
import com.bsarias.pokeapi.list.framework.local.ListLocalDataSourceImpl
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ListLocalDataSourceTest {
    private val mySharedPreferences = mockk<MySharedPreferences>(relaxed = true)
    private lateinit var listLocalDataSource: ListLocalDataSource

    private val results = listOf("bulbasaur", "squirtle", "charmander")
    private val key = "POKEMON"

    @Before
    fun init(){
        listLocalDataSource = ListLocalDataSourceImpl(mySharedPreferences)
    }

    @Test
    fun `should get pokemon list stored locally `(){

        every {
            mySharedPreferences.getStringSet(key)
        } answers {
            results
        }

        val list = listLocalDataSource.getListPokemon(key)

        Assert.assertEquals(results, list)
        Assert.assertEquals(3, list.size)
        Assert.assertNotEquals(0, list.size)

        verify(exactly = 1) {
            mySharedPreferences.getStringSet(key)
        }
    }

    @Test
    fun `should save pokemon list locally `(){

        listLocalDataSource.saveListPokemon(key, results)

        verify(exactly = 1) {
            mySharedPreferences.saveStringSet(key, results)
        }
    }

    @Test
    fun `should delete a pokemon list stored locally `(){

        listLocalDataSource.deleteListPokemon(key)

        verify(exactly = 1) {
            mySharedPreferences.delete(key)
        }
    }

}