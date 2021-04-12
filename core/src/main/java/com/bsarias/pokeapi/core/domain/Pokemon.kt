package com.bsarias.pokeapi.core.domain

data class Pokemon(
    var id: Int,
    var name: String,
    var sprites: List<Sprite>,
    var types: List<String>,
    var height: Int,
)