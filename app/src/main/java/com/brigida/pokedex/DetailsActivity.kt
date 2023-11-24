package com.brigida.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.brigida.pokedex.databinding.ActivityDetailsBinding
import com.brigida.pokedex.model.Pokemon
import com.brigida.pokedex.model.PokemonSpecies
import com.brigida.pokedex.network.ApiInterface
import com.brigida.pokedex.network.NetworkUtils
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    var weakness : MutableSet<String> = mutableSetOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fetchData()
    }

    private fun fetchData() {
        val number = intent.getIntExtra("pokemonNumber", 151)

        val apiService = NetworkUtils.getRetrofitInstance().create(ApiInterface::class.java)

        lifecycleScope.launch(Dispatchers.Main) {

            try {
                val response: Response<Pokemon> = apiService.getPokemon(number)
                if (response.isSuccessful) {
                    val data = response.body()
                    binding.apply {
                        if (data != null) {
                            tvName.text = data.name
                            if(data.id < 10) {
                                tvNumber.text = "#00${data.id}"
                            } else if (data.id < 100) {
                                tvNumber.text = "#0${data.id}"
                            } else {
                                tvNumber.text = "#${data.id}"
                            }

                            Picasso.get().load(data.sprites.other.officialArtwork.frontDefault).into(ivPokemon)

                            pbHp.progress = data.stats[0].baseStat
                            pbHp.max = 140
                            pbAttack.progress = data.stats[1].baseStat
                            pbAttack.max = 110
                            pbDefense.progress = data.stats[2].baseStat
                            pbDefense.max = 95
                            pbSpAtk.progress = data.stats[3].baseStat
                            pbSpAtk.max = 154
                            pbSpDef.progress = data.stats[4].baseStat
                            pbSpDef.max = 95
                            pbSpeed.progress = data.stats[5].baseStat
                            pbSpeed.max = 200

                            tvWeight.text = "${data.weight/10.0}kg"
                            tvHeight.text = "${data.height/10.0}m"

                            tvType1.text = data.types[0].type.name

                            when(data.types[0].type.name){
                                "normal"-> changeUiColor(R.color.normal, R.drawable.cv_name_bg_normal, R.drawable.normal_bg,R.drawable.normal, R.drawable.normal_cicle, listOf("Fighting"))
                                "fire"-> changeUiColor(R.color.fire, R.drawable.cv_name_bg_fire, R.drawable.fire_bg,R.drawable.fire, R.drawable.fire_cicle, listOf("Ground", "Rock", "Water"))
                                "water"-> changeUiColor(R.color.water, R.drawable.cv_name_bg_water, R.drawable.water_bg,R.drawable.water, R.drawable.water_cicle, listOf("Grass", "Electric"))
                                "grass"-> changeUiColor(R.color.grass, R.drawable.cv_name_bg_grass, R.drawable.grass_bg,R.drawable.grass, R.drawable.grass_cicle, listOf("Bug", "Fire", "Flying", "Poison", "Ice"))
                                "electric"-> changeUiColor(R.color.electric, R.drawable.cv_name_bg_electric, R.drawable.electric_bg,R.drawable.electric, R.drawable.electric_cicle, listOf("Ground"))
                                "ice"-> changeUiColor(R.color.ice, R.drawable.cv_name_bg_ice, R.drawable.ice_bg,R.drawable.ice, R.drawable.ice_cicle, listOf("Fire", "Fighting", "Steel", "Rock"))
                                "fighting"-> changeUiColor(R.color.fighting, R.drawable.cv_name_bg_fighting, R.drawable.fighting_bg,R.drawable.fighting, R.drawable.fighting_cicle, listOf("Fairy", "Flying", "Psychic"))
                                "poison"-> changeUiColor(R.color.poison, R.drawable.cv_name_bg_poison, R.drawable.poison_bg,R.drawable.poison, R.drawable.poison_cicle, listOf("Ground", "Psychic"))
                                "ground"-> changeUiColor(R.color.ground, R.drawable.cv_name_bg_ground, R.drawable.ground_bg,R.drawable.ground, R.drawable.ground_cicle, listOf("Ice", "Water", "Grass"))
                                "flying"-> changeUiColor(R.color.flying, R.drawable.cv_name_bg_flying, R.drawable.flying_bg,R.drawable.flying, R.drawable.flying_cicle, listOf("Electric", "Rock", "Ice"))
                                "psychic"-> changeUiColor(R.color.psychic, R.drawable.cv_name_bg_psychic, R.drawable.psychic_bg,R.drawable.psychic, R.drawable.psychic_cicle, listOf("Bug", "Dark", "Ghost"))
                                "bug"-> changeUiColor(R.color.bug, R.drawable.cv_name_bg_bug, R.drawable.bug_bg,R.drawable.bug, R.drawable.bug_cicle, listOf("Fire", "Flying", "Rock"))
                                "rock"-> changeUiColor(R.color.rock, R.drawable.cv_name_bg_rock, R.drawable.rock_bg,R.drawable.rock, R.drawable.rock_cicle, listOf("Fighting", "Grass", "Ground", "Steel", "Water"))
                                "ghost"-> changeUiColor(R.color.ghost, R.drawable.cv_name_bg_ghost, R.drawable.ghost_bg,R.drawable.ghost, R.drawable.ghost_cicle, listOf("Dark", "Ghost"))
                                "dragon"-> changeUiColor(R.color.dragon, R.drawable.cv_name_bg_dragon, R.drawable.dragon_bg,R.drawable.dragon, R.drawable.dragon_cicle, listOf("Dragon", "Fairy", "Ice"))
                                "dark"-> changeUiColor(R.color.dark, R.drawable.cv_name_bg_dark, R.drawable.dark_bg,R.drawable.dark, R.drawable.dark_cicle, listOf("Bug", "Fairy", "Fighting"))
                                "steel"-> changeUiColor(R.color.steel, R.drawable.cv_name_bg_steel, R.drawable.steel_bg,R.drawable.steel, R.drawable.steel_cicle, listOf("Ground", "Fire", "Fighting"))
                                "fairy"-> changeUiColor(R.color.fairy, R.drawable.cv_name_bg_fairy, R.drawable.fairy_bg,R.drawable.fairy, R.drawable.fairy_cicle, listOf("Poison", "Steel"))
                            }

                            if (data.types.size > 1) {
                                tvType2.text = data.types[1].type.name
                                when(data.types[1].type.name){
                                    "normal"-> type2Color(R.drawable.normal, R.drawable.normal_cicle, listOf("Fighting"))
                                    "fire"-> type2Color(R.drawable.fire, R.drawable.fire_cicle, listOf("Ground", "Rock", "Water"))
                                    "water"-> type2Color(R.drawable.water, R.drawable.water_cicle, listOf("Grass", "Electric"))
                                    "grass"-> type2Color(R.drawable.grass, R.drawable.grass_cicle, listOf("Bug", "Fire", "Flying", "Poison", "Ice"))
                                    "electric"-> type2Color(R.drawable.electric, R.drawable.electric_cicle, listOf("Ground"))
                                    "ice"-> type2Color(R.drawable.ice, R.drawable.ice_cicle, listOf("Fire", "Fighting", "Steel", "Rock"))
                                    "fighting"-> type2Color(R.drawable.fighting, R.drawable.fighting_cicle, listOf("Fairy", "Flying", "Psychic"))
                                    "poison"-> type2Color(R.drawable.poison, R.drawable.poison_cicle, listOf("Ground", "Psychic"))
                                    "ground"-> type2Color(R.drawable.ground, R.drawable.ground_cicle, listOf("Ice", "Water", "Grass"))
                                    "flying"-> type2Color(R.drawable.flying, R.drawable.flying_cicle, listOf("Electric", "Rock", "Ice"))
                                    "psychic"-> type2Color(R.drawable.psychic, R.drawable.psychic_cicle, listOf("Bug", "Dark", "Ghost"))
                                    "bug"-> type2Color(R.drawable.bug, R.drawable.bug_cicle, listOf("Fire", "Flying", "Rock"))
                                    "rock"-> type2Color(R.drawable.rock, R.drawable.rock_cicle, listOf("Fighting", "Grass", "Ground", "Steel", "Water"))
                                    "ghost"-> type2Color(R.drawable.ghost, R.drawable.ghost_cicle, listOf("Dark", "Ghost"))
                                    "dragon"-> type2Color(R.drawable.dragon, R.drawable.dragon_cicle,listOf("Dragon", "Fairy", "Ice"))
                                    "dark"-> type2Color(R.drawable.dark, R.drawable.dark_cicle, listOf("Bug", "Fairy", "Fighting"))
                                    "steel"-> type2Color(R.drawable.steel, R.drawable.steel_cicle, listOf("Ground", "Fire", "Fighting"))
                                    "fairy"-> type2Color(R.drawable.fairy, R.drawable.fairy_cicle, listOf("Poison", "Steel"))
                                }
                            } else {
                                ivType2.isVisible = false
                                tvType2.isVisible = false
                            }
                        }
                    }
                }
                val specieResponse: Response<PokemonSpecies> = apiService.getPokemonSpeciesDescription(number)
                if (specieResponse.isSuccessful) {
                    val data = specieResponse.body()
                    binding.apply {
                        if (data != null) {
                            tvDescription.text = data.flavor_text_entries[0].flavor_text.replace("\n", " ")
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("Error", e.message ?: "Unknown error")
            }
        }
    }

    private fun type2Color(typeImage: Int, typeBackground: Int, weaknesses: List<String>){
        weakness.addAll(weaknesses)
        binding.apply {
            ivType2.setImageResource(typeImage)
            ivType2.background = AppCompatResources.getDrawable(this@DetailsActivity, typeBackground)
        }
    }

    private fun changeUiColor(color: Int, cvNameBackground: Int, mainBackground: Int,typeImage: Int, typeBackground: Int, weaknesses: List<String>) {
        weakness.addAll(weaknesses)
        binding.apply {
            clPokemonName.background = AppCompatResources.getDrawable(this@DetailsActivity, cvNameBackground)
            clDetails.background = AppCompatResources.getDrawable(this@DetailsActivity, mainBackground)
            tvStats.setTextColor(getColor(color))
            tvCategoryTitle.setTextColor(getColor(color))
            tvWeightTitle.setTextColor(getColor(color))
            tvHeightTitle.setTextColor(getColor(color))
            tvWeaknesses.setTextColor(getColor(color))
            tvType.setTextColor(getColor(color))
            ivType1.setImageResource(typeImage)
            ivType1.background = AppCompatResources.getDrawable(this@DetailsActivity, typeBackground)
            tvNumber.setTextColor(getColor(color))
            pbAttack.progressTintList = getColorStateList(color)
            pbDefense.progressTintList = getColorStateList(color)
            pbHp.progressTintList = getColorStateList(color)
            pbSpeed.progressTintList = getColorStateList(color)
            pbSpAtk.progressTintList = getColorStateList(color)
            pbSpDef.progressTintList = getColorStateList(color)
        }
    }
}