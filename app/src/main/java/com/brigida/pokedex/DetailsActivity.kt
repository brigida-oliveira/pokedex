package com.brigida.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.content.res.AppCompatResources
import androidx.cardview.widget.CardView
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
            binding.apply {
                animationPokeball.visibility = View.VISIBLE
                animationLoading.visibility = View.VISIBLE
            }
            try {
                val response: Response<Pokemon> = apiService.getPokemon(number)
                if (response.isSuccessful) {
                    val data = response.body()
                    binding.apply {
                        constraintLayout.isVisible = true
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
                                "normal"-> changeUiColor(R.color.normal, R.drawable.cv_name_bg_normal, R.drawable.normal_bg,R.drawable.normal, R.drawable.normal_cicle, listOf("fighting"))
                                "fire"-> changeUiColor(R.color.fire, R.drawable.cv_name_bg_fire, R.drawable.fire_bg,R.drawable.fire, R.drawable.fire_cicle, listOf("ground", "rock", "water"))
                                "water"-> changeUiColor(R.color.water, R.drawable.cv_name_bg_water, R.drawable.water_bg,R.drawable.water, R.drawable.water_cicle, listOf("grass", "electric"))
                                "grass"-> changeUiColor(R.color.grass, R.drawable.cv_name_bg_grass, R.drawable.grass_bg,R.drawable.grass, R.drawable.grass_cicle, listOf("bug", "fire", "flying", "poison", "ice"))
                                "electric"-> changeUiColor(R.color.electric, R.drawable.cv_name_bg_electric, R.drawable.electric_bg,R.drawable.electric, R.drawable.electric_cicle, listOf("ground"))
                                "ice"-> changeUiColor(R.color.ice, R.drawable.cv_name_bg_ice, R.drawable.ice_bg,R.drawable.ice, R.drawable.ice_cicle, listOf("fire", "fighting", "steel", "rock"))
                                "fighting"-> changeUiColor(R.color.fighting, R.drawable.cv_name_bg_fighting, R.drawable.fighting_bg,R.drawable.fighting, R.drawable.fighting_cicle, listOf("fairy", "flying", "psychic"))
                                "poison"-> changeUiColor(R.color.poison, R.drawable.cv_name_bg_poison, R.drawable.poison_bg,R.drawable.poison, R.drawable.poison_cicle, listOf("ground", "psychic"))
                                "ground"-> changeUiColor(R.color.ground, R.drawable.cv_name_bg_ground, R.drawable.ground_bg,R.drawable.ground, R.drawable.ground_cicle, listOf("ice", "water", "grass"))
                                "flying"-> changeUiColor(R.color.flying, R.drawable.cv_name_bg_flying, R.drawable.flying_bg,R.drawable.flying, R.drawable.flying_cicle, listOf("electric", "rock", "ice"))
                                "psychic"-> changeUiColor(R.color.psychic, R.drawable.cv_name_bg_psychic, R.drawable.psychic_bg,R.drawable.psychic, R.drawable.psychic_cicle, listOf("bug", "dark", "ghost"))
                                "bug"-> changeUiColor(R.color.bug, R.drawable.cv_name_bg_bug, R.drawable.bug_bg,R.drawable.bug, R.drawable.bug_cicle, listOf("fire", "flying", "rock"))
                                "rock"-> changeUiColor(R.color.rock, R.drawable.cv_name_bg_rock, R.drawable.rock_bg,R.drawable.rock, R.drawable.rock_cicle, listOf("fighting", "grass", "ground", "steel", "water"))
                                "ghost"-> changeUiColor(R.color.ghost, R.drawable.cv_name_bg_ghost, R.drawable.ghost_bg,R.drawable.ghost, R.drawable.ghost_cicle, listOf("dark", "ghost"))
                                "dragon"-> changeUiColor(R.color.dragon, R.drawable.cv_name_bg_dragon, R.drawable.dragon_bg,R.drawable.dragon, R.drawable.dragon_cicle, listOf("dragon", "fairy", "ice"))
                                "dark"-> changeUiColor(R.color.dark, R.drawable.cv_name_bg_dark, R.drawable.dark_bg,R.drawable.dark, R.drawable.dark_cicle, listOf("bug", "fairy", "fighting"))
                                "steel"-> changeUiColor(R.color.steel, R.drawable.cv_name_bg_steel, R.drawable.steel_bg,R.drawable.steel, R.drawable.steel_cicle, listOf("ground", "fire", "fighting"))
                                "fairy"-> changeUiColor(R.color.fairy, R.drawable.cv_name_bg_fairy, R.drawable.fairy_bg,R.drawable.fairy, R.drawable.fairy_cicle, listOf("poison", "steel"))
                            }

                            if (data.types.size > 1) {
                                tvType2.text = data.types[1].type.name
                                when(data.types[1].type.name){
                                    "normal"-> type2Color(R.drawable.normal, R.drawable.normal_cicle, listOf("fighting"))
                                    "fire"-> type2Color(R.drawable.fire, R.drawable.fire_cicle, listOf("ground", "rock", "water"))
                                    "water"-> type2Color(R.drawable.water, R.drawable.water_cicle, listOf("grass", "electric"))
                                    "grass"-> type2Color(R.drawable.grass, R.drawable.grass_cicle, listOf("bug", "fire", "flying", "poison", "ice"))
                                    "electric"-> type2Color(R.drawable.electric, R.drawable.electric_cicle, listOf("ground"))
                                    "ice"-> type2Color(R.drawable.ice, R.drawable.ice_cicle, listOf("fire", "fighting", "steel", "rock"))
                                    "fighting"-> type2Color(R.drawable.fighting, R.drawable.fighting_cicle, listOf("fairy", "flying", "psychic"))
                                    "poison"-> type2Color(R.drawable.poison, R.drawable.poison_cicle, listOf("ground", "psychic"))
                                    "ground"-> type2Color(R.drawable.ground, R.drawable.ground_cicle, listOf("ice", "water", "grass"))
                                    "flying"-> type2Color(R.drawable.flying, R.drawable.flying_cicle, listOf("electric", "rock", "ice"))
                                    "psychic"-> type2Color(R.drawable.psychic, R.drawable.psychic_cicle, listOf("bug", "dark", "ghost"))
                                    "bug"-> type2Color(R.drawable.bug, R.drawable.bug_cicle, listOf("fire", "flying", "rock"))
                                    "rock"-> type2Color(R.drawable.rock, R.drawable.rock_cicle, listOf("fighting", "grass", "ground", "steel", "water"))
                                    "ghost"-> type2Color(R.drawable.ghost, R.drawable.ghost_cicle, listOf("dark", "ghost"))
                                    "dragon"-> type2Color(R.drawable.dragon, R.drawable.dragon_cicle,listOf("dragon", "fairy", "ice"))
                                    "dark"-> type2Color(R.drawable.dark, R.drawable.dark_cicle, listOf("bug", "fairy", "fighting"))
                                    "steel"-> type2Color(R.drawable.steel, R.drawable.steel_cicle, listOf("ground", "fire", "fighting"))
                                    "fairy"-> type2Color(R.drawable.fairy, R.drawable.fairy_cicle, listOf("poison", "steel"))
                                }

                                Log.d("weakness", weakness.elementAtOrNull(0).toString())

                            } else {
                                ivType2.isVisible = false
                                tvType2.isVisible = false
                            }

                            weaknessesCards()
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
                Log.e("error", e.message ?: "Unknown error")
            } finally {
                binding.apply {
                    animationPokeball.visibility = View.GONE
                    animationLoading.visibility = View.GONE
                }
            }
        }
    }

    private fun type2Color(typeImage: Int, typeBackground: Int, weaknesses: List<String>){
        Log.d("weakness2", weakness.toString())
        weakness.addAll(weaknesses)
        Log.d("weakness2", weakness.toString())
        binding.apply {
            ivType2.setImageResource(typeImage)
            ivType2.background = AppCompatResources.getDrawable(this@DetailsActivity, typeBackground)
        }
    }

    private fun weaknessesCards(){
        binding.apply {
            if(weakness.size < 2) {
                weaknessesVisibility(true, false, false, false, false, false, false, false)
            } else if(weakness.size < 3) {
                weaknessesVisibility(true, true, false, false, false, false, false, false)
            } else if(weakness.size < 4) {
                weaknessesVisibility(true, true, true, false, false, false, false, false)
            } else if(weakness.size < 5) {
                weaknessesVisibility(true, true, true, true, false, false, false, false)
            } else if(weakness.size < 6) {
                weaknessesVisibility(true, true, true, true, true, false, false, false)
            } else if(weakness.size < 7) {
                weaknessesVisibility(true, true, true, true, true, true, false, false)
            } else if(weakness.size < 8) {
                weaknessesVisibility(true, true, true, true, true, true, true, false)
            } else {
                weaknessesVisibility(true, true, true, true, true, true, true, true)
            }
        }
    }

    private fun weaknessesVisibility(type1: Boolean, type2: Boolean, type3: Boolean, type4: Boolean, type5: Boolean, type6: Boolean, type7: Boolean, type8: Boolean) {
        binding.apply {
            cvBackgroundWeaknessType1.isVisible = type1
            tvWeaknessType1.text = weakness.elementAtOrNull(0)
            verifyWeaknessType(weakness.elementAtOrNull(0).toString(), cvBackgroundWeaknessType1, ivWeaknessType1)

            cvBackgroundWeaknessType2.isVisible = type2
            tvWeaknessType2.text = weakness.elementAtOrNull(1)
            verifyWeaknessType(weakness.elementAtOrNull(1).toString(), cvBackgroundWeaknessType2, ivWeaknessType2)

            cvBackgroundWeaknessType3.isVisible = type3
            tvWeaknessType3.text = weakness.elementAtOrNull(2)
            verifyWeaknessType(weakness.elementAtOrNull(2).toString(), cvBackgroundWeaknessType3, ivWeaknessType3)

            cvBackgroundWeaknessType4.isVisible = type4
            tvWeaknessType4.text = weakness.elementAtOrNull(3)
            verifyWeaknessType(weakness.elementAtOrNull(3).toString(), cvBackgroundWeaknessType4, ivWeaknessType4)

            cvBackgroundWeaknessType5.isVisible = type5
            tvWeaknessType5.text = weakness.elementAtOrNull(4)
            verifyWeaknessType(weakness.elementAtOrNull(4).toString(), cvBackgroundWeaknessType5, ivWeaknessType5)

            cvBackgroundWeaknessType6.isVisible = type6
            tvWeaknessType6.text = weakness.elementAtOrNull(5)
            verifyWeaknessType(weakness.elementAtOrNull(5).toString(), cvBackgroundWeaknessType6, ivWeaknessType6)

            cvBackgroundWeaknessType7.isVisible = type7
            tvWeaknessType7.text = weakness.elementAtOrNull(6)
            verifyWeaknessType(weakness.elementAtOrNull(6).toString(), cvBackgroundWeaknessType7, ivWeaknessType7)

            cvBackgroundWeaknessType8.isVisible = type8
            tvWeaknessType8.text = weakness.elementAtOrNull(7)
            verifyWeaknessType(weakness.elementAtOrNull(7).toString(), cvBackgroundWeaknessType8, ivWeaknessType8)
        }
    }

    private fun verifyWeaknessType(type: String, cardView: CardView, imageView: ImageView) {
        when(type) {
            "normal"-> {
                cardView.setCardBackgroundColor(getColor(R.color.normal))
                imageView.setImageResource(R.drawable.normal)
            }
            "fire"-> {
                cardView.setCardBackgroundColor(getColor(R.color.fire))
                imageView.setImageResource(R.drawable.fire)
            }
            "water"-> {
                cardView.setCardBackgroundColor(getColor(R.color.water))
                imageView.setImageResource(R.drawable.water)
            }
            "grass"-> {
                cardView.setCardBackgroundColor(getColor(R.color.grass))
                imageView.setImageResource(R.drawable.grass)
            }
            "electric"-> {
                cardView.setCardBackgroundColor(getColor(R.color.electric))
                imageView.setImageResource(R.drawable.electric)
            }
            "ice"-> {
                cardView.setCardBackgroundColor(getColor(R.color.ice))
                imageView.setImageResource(R.drawable.ice)
            }
            "fighting"-> {
                cardView.setCardBackgroundColor(getColor(R.color.fighting))
                imageView.setImageResource(R.drawable.fighting)
            }
            "poison"-> {
                cardView.setCardBackgroundColor(getColor(R.color.poison))
                imageView.setImageResource(R.drawable.poison)
            }
            "ground"-> {
                cardView.setCardBackgroundColor(getColor(R.color.ground))
                imageView.setImageResource(R.drawable.ground)
            }
            "flying"-> {
                cardView.setCardBackgroundColor(getColor(R.color.flying))
                imageView.setImageResource(R.drawable.flying)
            }
            "psychic"-> {
                cardView.setCardBackgroundColor(getColor(R.color.psychic))
                imageView.setImageResource(R.drawable.psychic)
            }
            "bug"-> {
                cardView.setCardBackgroundColor(getColor(R.color.bug))
                imageView.setImageResource(R.drawable.bug)
            }
            "rock"-> {
                cardView.setCardBackgroundColor(getColor(R.color.rock))
                imageView.setImageResource(R.drawable.rock)
            }
            "ghost"-> {
                cardView.setCardBackgroundColor(getColor(R.color.ghost))
                imageView.setImageResource(R.drawable.ghost)
            }
            "dragon"-> {
                cardView.setCardBackgroundColor(getColor(R.color.dragon))
                imageView.setImageResource(R.drawable.dragon)
            }
            "dark"-> {
                cardView.setCardBackgroundColor(getColor(R.color.dark))
                imageView.setImageResource(R.drawable.dark)
            }
            "steel"-> {
                cardView.setCardBackgroundColor(getColor(R.color.steel))
                imageView.setImageResource(R.drawable.steel)
            }
            "fairy"-> {
                cardView.setCardBackgroundColor(getColor(R.color.fairy))
                imageView.setImageResource(R.drawable.fairy)
            }
        }
    }

    private fun changeUiColor(color: Int, cvNameBackground: Int, mainBackground: Int,typeImage: Int, typeBackground: Int, weaknesses: List<String>) {
        Log.d("weakness1", weakness.toString())
        weakness.addAll(weaknesses)
        Log.d("weakness1", weakness.toString())
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