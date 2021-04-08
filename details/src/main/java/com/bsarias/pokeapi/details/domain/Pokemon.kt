package com.bsarias.pokeapi.details.domain

data class Pokemon(
    var id: Int,
    var name: String,
    var sprites: List<Sprite>,
    var types: List<String>,
    var height: Int,
)