package com.brigida.pokedex.model

import com.google.gson.annotations.SerializedName

data class Ability(
    val ability: AbilityX,
    @SerializedName("is_hidden")
    val isHidden: Boolean,
    val slot: Int
)