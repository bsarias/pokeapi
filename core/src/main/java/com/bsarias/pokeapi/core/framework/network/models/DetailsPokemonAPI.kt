package com.bsarias.pokeapi.core.framework.network.models

import com.bsarias.pokeapi.core.domain.Pokemon
import com.google.gson.annotations.SerializedName

data class PokemonAPI(
    @SerializedName("height") val height: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("sprites") val sprites: SpritesAPI,
    @SerializedName("types") val types: List<TypesAPI>,
    @SerializedName("weight") val weight: Int
) {
    fun mapToDomain(): Pokemon {
        val types = this.types.map {
            it.type.name
        }
        return Pokemon(
            id = this.id,
            name = this.name,
            height = this.height,
            types = types.toString(),
            weight = this.weight,
            frontDefault = this.sprites.frontDefault,
            officialArtwork = this.sprites.other.officialArtwork.frontDefault,
        )
    }
}

data class SpritesAPI(
    @SerializedName("other") val other: OtherAPI,
    @SerializedName("front_default") val frontDefault: String,
)

data class TypesAPI(
    @SerializedName("type") val type: TypeAPI
)

data class TypeAPI(
    @SerializedName("name") val name: String,
)


data class OtherAPI(
    @SerializedName("official-artwork") val officialArtwork: OfficialArtworkAPI
)

data class OfficialArtworkAPI(
    @SerializedName("front_default") val frontDefault: String
)
