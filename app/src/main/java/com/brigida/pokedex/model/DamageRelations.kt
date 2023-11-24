package com.brigida.pokedex.model

import com.google.gson.annotations.SerializedName

data class DamageRelations(
    @SerializedName("damage_relations")
    val damageRelations: DamageRelationsX,
    @SerializedName("game_indices")
    val gameIndices: List<GameIndiceX>,
    val generation: GenerationXX,
    val id: Int,
    @SerializedName("move_damage_class")
    val moveDamageClass: MoveDamageClass,
    val moves: List<MoveXX>,
    val name: String,
    val names: List<NameX>,
    @SerializedName("past_damage_relations")
    val pastDamageRelations: List<Any>,
    val pokemon: List<PokemonXX>
)