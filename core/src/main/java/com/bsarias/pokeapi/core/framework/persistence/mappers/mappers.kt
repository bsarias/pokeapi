package com.bsarias.pokeapi.core.framework.persistence.mappers

import com.bsarias.pokeapi.core.domain.Pokemon
import com.bsarias.pokeapi.core.framework.persistence.entities.PokemonDB

fun Pokemon.mapToEntity() = PokemonDB(
    id= id,
    name= name,
    types = types,
    weight = weight,
    height = height,
    frontDefault = frontDefault,
    officialArtwork = officialArtwork
)