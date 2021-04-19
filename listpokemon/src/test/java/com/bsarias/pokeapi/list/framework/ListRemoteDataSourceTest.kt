package com.bsarias.pokeapi.list.framework

import android.accounts.NetworkErrorException
import com.bsarias.pokeapi.core.domain.ListPokemon
import com.bsarias.pokeapi.core.framework.network.models.SearchAPI
import com.bsarias.pokeapi.list.data.source.ListRemoteDataSource
import com.bsarias.pokeapi.list.framework.network.ListPokemonService
import com.bsarias.pokeapi.list.framework.network.ListRemoteDataSourceImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.lang.Exception

class ListRemoteDataSourceTest {

    private val listPokemonService = mockk<ListPokemonService>(relaxed = true)
    private lateinit var listRemoteDataSource: ListRemoteDataSource

    private val results = listOf("bulbasaur", "squirtle", "charmander")
    private val offset = 0
    private val limit = 100

    @Before
    fun init(){
        listRemoteDataSource = ListRemoteDataSourceImpl(listPokemonService)
    }

    @Test
    fun `when data is got successfully`() {
        coEvery {
            listPokemonService.getListPokemonAsync(offset, limit).mapToDomain()
        } answers {
            ListPokemon(results)
        }

        runBlocking {
            val response = listRemoteDataSource.doSearch(offset, limit)
            Assert.assertEquals(results, response.results)
        }

        coVerify(exactly = 1) {
            listPokemonService.getListPokemonAsync(offset, limit)
        }

    }

    @Test(expected = Exception::class)
    fun `when is thrown an Exception`(){
        coEvery {
            listPokemonService.getListPokemonAsync(offset, limit)
        } answers {
            throw NetworkErrorException()
        }

        runBlocking {
            listRemoteDataSource.doSearch(offset, limit)
        }

    }

}