package com.bsarias.pokeapi.list.usecases

import com.bsarias.pokeapi.core.domain.ListPokemon
import com.bsarias.pokeapi.list.data.repository.ListPokemonRepository

class GetListPokemons(private val listPokemonRepository: ListPokemonRepository) {
    suspend fun invoke(limit: Int, offset: Int, key: String): ListPokemon =
        listPokemonRepository.listPokemons(offset, limit, key)
}