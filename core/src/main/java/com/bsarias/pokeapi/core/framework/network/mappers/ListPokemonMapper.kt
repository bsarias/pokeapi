package com.bsarias.pokeapi.core.framework.network.mappers

import com.bsarias.pokeapi.core.domain.ListPokemon
import com.bsarias.pokeapi.core.framework.network.models.SearchAPI

object ListPokemonMapper {
    fun map(search: SearchAPI): ListPokemon {
        return ListPokemon(search.results.map { it.name })
    }
}