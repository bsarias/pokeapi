package com.bsarias.pokeapi.details.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bsarias.pokeapi.core.framework.di.IoDispatcher
import com.bsarias.pokeapi.details.usecases.GetPokemonByName
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val getPokemonByName: GetPokemonByName,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) :
    ViewModel() {

    private lateinit var pokemonName: String

    private val model: MutableLiveData<DetailsViewState> = MutableLiveData()

    fun getDetail(pokemonName: String): LiveData<DetailsViewState> {
        this.pokemonName = pokemonName
        return model
    }

    fun loadDetails() {
        viewModelScope.launch(ioDispatcher) {
            model.postValue(DetailsViewState.Loading)
            getPokemonByName(pokemonName)
                .catch { exception ->
                    model.postValue(DetailsViewState.Error(exception.localizedMessage!!))
                }
                .collect {
                    model.postValue(DetailsViewState.Success(it))
                }
        }
    }
}