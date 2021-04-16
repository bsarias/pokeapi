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
import kotlinx.coroutines.flow.collectLatest
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
            lateinit var result: ListViewState
            getListPokemons(0, 151, "pokemons")
                .catch { exception -> result = ListViewState.Error(exception.localizedMessage!!) }
                .collect {
                    result = ListViewState.Success(it)
                }
            model.postValue(result)
        }
    }

}



