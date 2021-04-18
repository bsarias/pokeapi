package com.bsarias.pokeapi.core.framework.persistence.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bsarias.pokeapi.core.domain.Pokemon

@Entity
data class PokemonDB(
    @PrimaryKey var id: Int,
    var name: String,
    var types: String,
    var height: Int,
    var weight: Int,
    var frontDefault: String,
    var officialArtwork: String,
) {
    fun mapToDomain(): Pokemon {
        return Pokemon(
            id = this.id,
            name = this.name,
            height = this.height,
            types = types,
            weight = this.weight,
            frontDefault = this.frontDefault,
            officialArtwork = this.officialArtwork,
        )
    }
    constructor(pokemon: Pokemon) : this(
        id = pokemon.id,
        name = pokemon.name,
        types = pokemon.types,
        weight = pokemon.weight,
        height = pokemon.height,
        frontDefault = pokemon.frontDefault,
        officialArtwork = pokemon.officialArtwork
    )

}
