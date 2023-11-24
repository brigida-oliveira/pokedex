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

                            var weakness : MutableSet<String> = mutableSetOf()

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
                                "normal"-> {
                                    weakness.addAll(listOf("Fighting"))
                                    changeUiColor(R.color.normal, R.drawable.cv_name_bg_normal, R.drawable.normal_bg,R.drawable.normal, R.drawable.normal_cicle)
                                }
                                "fire"-> {
                                    weakness.addAll(listOf("Ground", "Rock", "Water"))
                                    changeUiColor(R.color.fire, R.drawable.cv_name_bg_fire, R.drawable.fire_bg,R.drawable.fire, R.drawable.fire_cicle)
                                }
                                "water"-> {
                                    weakness.addAll(listOf("Grass", "Water"))
                                    changeUiColor(R.color.water, R.drawable.cv_name_bg_water, R.drawable.water_bg,R.drawable.water, R.drawable.water_cicle)
                                }
                                "grass"-> {
                                    weakness.addAll(listOf("Bug", "Fire", "Flying", "Poison", "Ice"))
                                    changeUiColor(R.color.grass, R.drawable.cv_name_bg_grass, R.drawable.grass_bg,R.drawable.grass, R.drawable.grass_cicle)
                                }
                                "electric"-> {
                                    weakness.addAll(listOf("Ground"))
                                    changeUiColor(R.color.electric, R.drawable.cv_name_bg_electric, R.drawable.electric_bg,R.drawable.electric, R.drawable.electric_cicle)
                                }
                                "ice"-> {
                                    weakness.addAll(listOf("Fire", "Fighting", "Steel", "Rock"))
                                    changeUiColor(R.color.ice, R.drawable.cv_name_bg_ice, R.drawable.ice_bg,R.drawable.ice, R.drawable.ice_cicle)
                                }
                                "fighting"-> {
                                    weakness.addAll(listOf("Fairy", "Flying", "Psychic"))
                                    changeUiColor(R.color.fighting, R.drawable.cv_name_bg_fighting, R.drawable.fighting_bg,R.drawable.fighting, R.drawable.fighting_cicle)
                                }
                                "poison"-> {
                                    weakness.addAll(listOf("Ground", "Psychic"))
                                    changeUiColor(R.color.poison, R.drawable.cv_name_bg_poison, R.drawable.poison_bg,R.drawable.poison, R.drawable.poison_cicle)
                                }
                                "ground"-> {
                                    weakness.addAll(listOf("Ice", "Water", "Grass"))
                                    changeUiColor(R.color.ground, R.drawable.cv_name_bg_ground, R.drawable.ground_bg,R.drawable.ground, R.drawable.ground_cicle)
                                }
                                "flying"-> {
                                    weakness.addAll(listOf("Electric", "Rock", "Ice"))
                                    changeUiColor(R.color.flying, R.drawable.cv_name_bg_flying, R.drawable.flying_bg,R.drawable.flying, R.drawable.flying_cicle)
                                }
                                "psychic"-> {
                                    weakness.addAll(listOf("Bug", "Dark", "Ghost"))
                                    changeUiColor(R.color.psychic, R.drawable.cv_name_bg_psychic, R.drawable.psychic_bg,R.drawable.psychic, R.drawable.psychic_cicle)
                                }
                                "bug"-> {
                                    weakness.addAll(listOf("Fire", "Flying", "Rock"))
                                    changeUiColor(R.color.bug, R.drawable.cv_name_bg_bug, R.drawable.bug_bg,R.drawable.bug, R.drawable.bug_cicle)
                                }
                                "rock"-> {
                                    weakness.addAll(listOf("Fighting", "Grass", "Ground", "Steel", "Water"))
                                    changeUiColor(R.color.rock, R.drawable.cv_name_bg_rock, R.drawable.rock_bg,R.drawable.rock, R.drawable.rock_cicle)
                                }
                                "ghost"-> {
                                    weakness.addAll(listOf("Dark", "Ghost"))
                                    changeUiColor(R.color.ghost, R.drawable.cv_name_bg_ghost, R.drawable.ghost_bg,R.drawable.ghost, R.drawable.ghost_cicle)
                                }
                                "dragon"-> {
                                    weakness.addAll(listOf("Dragon", "Fairy", "Ice"))
                                    changeUiColor(R.color.dragon, R.drawable.cv_name_bg_dragon, R.drawable.dragon_bg,R.drawable.dragon, R.drawable.dragon_cicle)
                                }
                                "dark"-> {
                                    weakness.addAll(listOf("Bug", "Fairy", "Fighting"))
                                    changeUiColor(R.color.dark, R.drawable.cv_name_bg_dark, R.drawable.dark_bg,R.drawable.dark, R.drawable.dark_cicle)
                                }
                                "steel"-> {
                                    weakness.addAll(listOf("Ground", "Fire", "Fighting"))
                                    changeUiColor(R.color.steel, R.drawable.cv_name_bg_steel, R.drawable.steel_bg,R.drawable.steel, R.drawable.steel_cicle)
                                }
                                "fairy"-> {
                                    weakness.addAll(listOf("Poison", "Steel"))
                                    changeUiColor(R.color.fairy, R.drawable.cv_name_bg_fairy, R.drawable.fairy_bg,R.drawable.fairy, R.drawable.fairy_cicle)
                                }
                            }

                            if (data.types.size > 1) {
                                tvType2.text = data.types[1].type.name
                                when(data.types[1].type.name){
                                    "normal"-> {
                                        ivType2.setImageResource(R.drawable.normal)
                                        ivType2.background = AppCompatResources.getDrawable(this@DetailsActivity, R.drawable.normal_cicle)
                                    }
                                    "fire"-> {
                                        ivType2.setImageResource(R.drawable.fire)
                                        ivType2.background = AppCompatResources.getDrawable(this@DetailsActivity, R.drawable.fire_cicle)
                                    }
                                    "water"-> {
                                        ivType2.setImageResource(R.drawable.water)
                                        ivType2.background = AppCompatResources.getDrawable(this@DetailsActivity, R.drawable.water_cicle)
                                    }
                                    "grass"-> {
                                        ivType2.setImageResource(R.drawable.grass)
                                        ivType2.background = AppCompatResources.getDrawable(this@DetailsActivity, R.drawable.grass_cicle)
                                    }
                                    "electric"-> {
                                        ivType2.setImageResource(R.drawable.electric)
                                        ivType2.background = AppCompatResources.getDrawable(this@DetailsActivity, R.drawable.electric_cicle)
                                    }
                                    "ice"-> {
                                        ivType2.setImageResource(R.drawable.ice)
                                        ivType2.background = AppCompatResources.getDrawable(this@DetailsActivity, R.drawable.ice_cicle)
                                    }
                                    "fighting"-> {
                                        ivType2.setImageResource(R.drawable.fighting)
                                        ivType2.background = AppCompatResources.getDrawable(this@DetailsActivity, R.drawable.fighting_cicle)
                                    }
                                    "poison"-> {
                                        ivType2.setImageResource(R.drawable.poison)
                                        ivType2.background = AppCompatResources.getDrawable(this@DetailsActivity, R.drawable.poison_cicle)
                                    }
                                    "ground"-> {
                                        ivType2.setImageResource(R.drawable.ground)
                                        ivType2.background = AppCompatResources.getDrawable(this@DetailsActivity, R.drawable.ground_cicle)
                                    }
                                    "flying"-> {
                                        ivType2.setImageResource(R.drawable.flying)
                                        ivType2.background = AppCompatResources.getDrawable(this@DetailsActivity, R.drawable.flying_cicle)
                                    }
                                    "psychic"-> {
                                        ivType2.setImageResource(R.drawable.psychic)
                                        ivType2.background = AppCompatResources.getDrawable(this@DetailsActivity, R.drawable.psychic_cicle)
                                    }
                                    "bug"-> {
                                        ivType2.setImageResource(R.drawable.bug)
                                        ivType2.background = AppCompatResources.getDrawable(this@DetailsActivity, R.drawable.bug_cicle)
                                    }
                                    "rock"-> {
                                        weakness.addAll(listOf("Fighting", "Grass", "Ground", "Steel", "Water"))
                                        ivType2.setImageResource(R.drawable.rock)
                                        ivType2.background = AppCompatResources.getDrawable(this@DetailsActivity, R.drawable.rock_cicle)
                                    }
                                    "ghost"-> {
                                        weakness.addAll(listOf("Dark", "Ghost"))
                                        ivType2.setImageResource(R.drawable.ghost)
                                        ivType2.background = AppCompatResources.getDrawable(this@DetailsActivity, R.drawable.ghost_cicle)
                                    }
                                    "dragon"-> {
                                        weakness.addAll(listOf("Dragon", "Fairy", "Ice"))
                                        ivType2.setImageResource(R.drawable.dragon)
                                        ivType2.background = AppCompatResources.getDrawable(this@DetailsActivity, R.drawable.dragon_cicle)
                                    }
                                    "dark"-> {
                                        weakness.addAll(listOf("Bug", "Fairy", "Fighting"))
                                        ivType2.setImageResource(R.drawable.dark)
                                        ivType2.background = AppCompatResources.getDrawable(this@DetailsActivity,
                                            R.drawable.dark_cicle
                                        )
                                    }
                                    "steel"-> {
                                        weakness.addAll(listOf("Ground", "Fire", "Fighting"))
                                        ivType2.setImageResource(R.drawable.steel)
                                        ivType2.background = AppCompatResources.getDrawable(this@DetailsActivity, R.drawable.steel_cicle)
                                    }
                                    "fairy"-> {
                                        weakness.addAll(listOf("Poison", "Steel"))
                                        ivType2.setImageResource(R.drawable.fairy)
                                        ivType2.background = AppCompatResources.getDrawable(this@DetailsActivity, R.drawable.fairy_cicle)
                                    }
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

    private fun changeUiColor(color: Int, cvNameBackground: Int, mainBackground: Int,typeImage: Int, typeBackground: Int) {
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