package com.bsarias.pokeapi.list.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsarias.pokeapi.core.domain.ListPokemon
import com.bsarias.pokeapi.list.usecases.GetListPokemons
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
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
            loadPokemons()
        }
    }

    fun getPokemons(): LiveData<ListViewState> {
        return model
    }

    private fun loadPokemons() {
        viewModelScope.launch {
            model.value = ListViewState.Loading
            getListPokemons(0, 151, "pokemons")
                .catch { exception ->
                    model.postValue(ListViewState.Error(exception.localizedMessage!!))
                }
                .flowOn(Dispatchers.IO)
                .collect {
                    model.value = ListViewState.Success(it)
                }
        }
    }

}



