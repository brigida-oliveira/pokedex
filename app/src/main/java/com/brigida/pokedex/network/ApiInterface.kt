package com.brigida.pokedex.network

import com.brigida.pokedex.model.Pokemon
import com.brigida.pokedex.model.PokemonSpecies
import com.brigida.pokedex.model.Pokemons
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("pokemon")
    suspend fun listPokemons(@Query("offset") offset: Int, @Query("limit") limit: Int): Response<Pokemons>

    @GET("pokemon/{number}")
    suspend fun getPokemon(@Path("number") number: Int): Response<Pokemon>

    @GET("pokemon-species/{number}")
    suspend fun getPokemonSpeciesDescription(@Path("number") number: Int): Response<PokemonSpecies>
}