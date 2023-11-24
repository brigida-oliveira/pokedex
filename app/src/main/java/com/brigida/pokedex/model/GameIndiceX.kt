package com.brigida.pokedex.model

import com.google.gson.annotations.SerializedName

data class GameIndiceX(
    @SerializedName("game_index")
    val gameIndex: Int,
    val generation: GenerationXX
)