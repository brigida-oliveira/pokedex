package com.brigida.pokedex.model

data class Pokemons(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)