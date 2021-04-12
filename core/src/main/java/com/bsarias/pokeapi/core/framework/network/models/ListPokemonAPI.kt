package com.bsarias.pokeapi.core.framework.network.models

import com.google.gson.annotations.SerializedName

data class SearchAPI(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String,
    @SerializedName("previous") val previous: String,
    @SerializedName("results") val results: List<ResultAPI>
)

data class ResultAPI(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)