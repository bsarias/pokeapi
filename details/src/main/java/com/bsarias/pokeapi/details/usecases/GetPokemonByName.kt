package com.bsarias.pokeapi.details.usecases

import com.bsarias.pokeapi.core.domain.Pokemon
import com.bsarias.pokeapi.details.data.repository.DetailsRepository
import kotlinx.coroutines.flow.Flow

class GetPokemonByName(private val detailsRepository: DetailsRepository) {
    operator fun invoke(pokemonName: String): Flow<Pokemon> =
        detailsRepository.getPokemonByName(pokemonName)
}