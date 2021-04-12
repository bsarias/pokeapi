package com.bsarias.pokeapi.list.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsarias.pokeapi.core.domain.ListPokemon
import com.bsarias.pokeapi.list.usecases.GetListPokemons
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListViewModel @Inject constructor(private val getListPokemons: GetListPokemons) :
    ViewModel() {

    sealed class ListViewState {
        object Loading : ListViewState()
        class Success(val listPokemon: ListPokemon) : ListViewState()
        class Error(val error: String) : ListViewState()
    }

    private val model: MutableLiveData<ListViewState> by lazy {
        MutableLiveData<ListViewState>().also {
            loadUsers()
        }
    }

    fun getPokemons(): LiveData<ListViewState> {
        return model
    }

    private fun loadUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            model.postValue(ListViewState.Loading)
            val result = try {
                val pokemons = getListPokemons(100, 50, "pokemons")
                ListViewState.Success(pokemons)
            } catch (e: Exception) {
                ListViewState.Error(e.localizedMessage!!)
            }
            model.postValue(result)
        }
    }

}



