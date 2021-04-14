package com.bsarias.pokeapi.list.usecases

import com.bsarias.pokeapi.core.domain.ListPokemon
import com.bsarias.pokeapi.list.data.repository.ListPokemonRepository
import kotlinx.coroutines.flow.Flow

class GetListPokemons(private val listPokemonRepository: ListPokemonRepository) {
    suspend operator fun invoke(offset: Int, limit: Int, key: String): Flow<ListPokemon> =
        listPokemonRepository.listPokemons(offset, limit, key)
}