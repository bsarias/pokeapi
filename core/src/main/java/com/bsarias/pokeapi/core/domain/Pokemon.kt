package com.bsarias.pokeapi.core.domain

data class Pokemon(
    var id: Int,
    var name: String,
    var types: String,
    var height: Int,
    var weight: Int,
    var frontDefault: String,
    var officialArtwork: String,
)