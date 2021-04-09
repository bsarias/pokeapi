package com.bsarias.pokeapi.list.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bsarias.pokeapi.core.domain.ListPokemon
import com.bsarias.pokeapi.list.usecases.GetListPokemons
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.reflect.KProperty

class ListViewModel @Inject constructor(private val getListPokemons: GetListPokemons) :
    ViewModel() {


    private val pokemons: MutableLiveData<ListPokemon> by lazy {
        MutableLiveData<ListPokemon>().also {
            loadUsers()

        }
    }

    fun getPokemons(): LiveData<ListPokemon> {
        return pokemons
    }

    private fun loadUsers() {
        GlobalScope.launch {
            pokemons.postValue(getListPokemons.invoke(100, 50, "pokemons"))
        }
    }

}

