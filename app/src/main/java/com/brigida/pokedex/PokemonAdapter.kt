package com.brigida.pokedex

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.brigida.pokedex.databinding.ItemLayoutBinding
import com.brigida.pokedex.model.Pokemon
import com.squareup.picasso.Picasso

class PokemonAdapter(private val context: Context,private val pokemonList: MutableList<Pokemon>, private var itemClickListener: OnItemClickListener? = null): RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(pokemon: Pokemon)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val listItem = ItemLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return PokemonViewHolder(listItem)
    }

    override fun getItemCount() = pokemonList.size

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.pokemonName.text = pokemonList[position].name

        if(pokemonList[position].id < 10) {
            holder.pokemonNumber.text = "#00${pokemonList[position].id}"
        } else if (pokemonList[position].id < 100) {
            holder.pokemonNumber.text = "#0${pokemonList[position].id}"
        } else {
            holder.pokemonNumber.text = "#${pokemonList[position].id}"
        }

        holder.pokemonType1.text = pokemonList[position].types[0].type.name
        when(pokemonList[position].types[0].type.name){
            "normal"-> {
                holder.pokemonType1Background.setCardBackgroundColor(ContextCompat.getColor(context,R.color.normal))
                holder.pokemonBackground.setCardBackgroundColor(ContextCompat.getColor(context,R.color.normal_bg))
                holder.pokemonIvType1.setImageResource(R.drawable.normal)
            }
            "fire"-> {
                holder.pokemonType1Background.setCardBackgroundColor(ContextCompat.getColor(context,R.color.fire))
                holder.pokemonBackground.setCardBackgroundColor(ContextCompat.getColor(context,R.color.fire_bg))
                holder.pokemonIvType1.setImageResource(R.drawable.fire)
            }
            "water"-> {
                holder.pokemonType1Background.setCardBackgroundColor(ContextCompat.getColor(context,R.color.water))
                holder.pokemonBackground.setCardBackgroundColor(ContextCompat.getColor(context,R.color.water_bg))
                holder.pokemonIvType1.setImageResource(R.drawable.water)
            }
            "grass"-> {
                holder.pokemonType1Background.setCardBackgroundColor(ContextCompat.getColor(context,R.color.grass))
                holder.pokemonBackground.setCardBackgroundColor(ContextCompat.getColor(context,R.color.grass_bg))
                holder.pokemonIvType1.setImageResource(R.drawable.grass)
            }
            "electric"-> {
                holder.pokemonType1Background.setCardBackgroundColor(ContextCompat.getColor(context,R.color.electric))
                holder.pokemonBackground.setCardBackgroundColor(ContextCompat.getColor(context,R.color.electric_bg))
                holder.pokemonIvType1.setImageResource(R.drawable.electric)
            }
            "ice"-> {
                holder.pokemonType1Background.setCardBackgroundColor(ContextCompat.getColor(context,R.color.ice))
                holder.pokemonBackground.setCardBackgroundColor(ContextCompat.getColor(context,R.color.ice_bg))
                holder.pokemonIvType1.setImageResource(R.drawable.ice)
            }
            "fighting"-> {
                holder.pokemonType1Background.setCardBackgroundColor(ContextCompat.getColor(context,R.color.fighting))
                holder.pokemonBackground.setCardBackgroundColor(ContextCompat.getColor(context,R.color.fighting_bg))
                holder.pokemonIvType1.setImageResource(R.drawable.fighting)
            }
            "poison"-> {
                holder.pokemonType1Background.setCardBackgroundColor(ContextCompat.getColor(context,R.color.poison))
                holder.pokemonBackground.setCardBackgroundColor(ContextCompat.getColor(context,R.color.poison_bg))
                holder.pokemonIvType1.setImageResource(R.drawable.poison)
            }
            "ground"-> {
                holder.pokemonType1Background.setCardBackgroundColor(ContextCompat.getColor(context,R.color.ground))
                holder.pokemonBackground.setCardBackgroundColor(ContextCompat.getColor(context,R.color.ground_bg))
                holder.pokemonIvType1.setImageResource(R.drawable.ground)
            }
            "flying"-> {
                holder.pokemonType1Background.setCardBackgroundColor(ContextCompat.getColor(context,R.color.flying))
                holder.pokemonBackground.setCardBackgroundColor(ContextCompat.getColor(context,R.color.flying_bg))
                holder.pokemonIvType1.setImageResource(R.drawable.flying)
            }
            "psychic"-> {
                holder.pokemonType1Background.setCardBackgroundColor(ContextCompat.getColor(context,R.color.psychic))
                holder.pokemonBackground.setCardBackgroundColor(ContextCompat.getColor(context,R.color.psychic_bg))
                holder.pokemonIvType1.setImageResource(R.drawable.psychic)
            }
            "bug"-> {
                holder.pokemonType1Background.setCardBackgroundColor(ContextCompat.getColor(context,R.color.bug))
                holder.pokemonBackground.setCardBackgroundColor(ContextCompat.getColor(context,R.color.bug_bg))
                holder.pokemonIvType1.setImageResource(R.drawable.bug)
            }
            "rock"-> {
                holder.pokemonType1Background.setCardBackgroundColor(ContextCompat.getColor(context,R.color.rock))
                holder.pokemonBackground.setCardBackgroundColor(ContextCompat.getColor(context,R.color.rock_bg))
                holder.pokemonIvType1.setImageResource(R.drawable.rock)
            }
            "ghost"-> {
                holder.pokemonType1Background.setCardBackgroundColor(ContextCompat.getColor(context,R.color.ghost))
                holder.pokemonBackground.setCardBackgroundColor(ContextCompat.getColor(context,R.color.ghost_bg))
                holder.pokemonIvType1.setImageResource(R.drawable.ghost)
            }
            "dragon"-> {
                holder.pokemonType1Background.setCardBackgroundColor(ContextCompat.getColor(context,R.color.dragon))
                holder.pokemonBackground.setCardBackgroundColor(ContextCompat.getColor(context,R.color.dragon_bg))
                holder.pokemonIvType1.setImageResource(R.drawable.dragon)
            }
            "dark"-> {
                holder.pokemonType1Background.setCardBackgroundColor(ContextCompat.getColor(context,R.color.dark))
                holder.pokemonBackground.setCardBackgroundColor(ContextCompat.getColor(context,R.color.dark_bg))
                holder.pokemonIvType1.setImageResource(R.drawable.dark)
            }
            "steel"-> {
                holder.pokemonType1Background.setCardBackgroundColor(ContextCompat.getColor(context,R.color.steel))
                holder.pokemonBackground.setCardBackgroundColor(ContextCompat.getColor(context,R.color.steel_bg))
                holder.pokemonIvType1.setImageResource(R.drawable.steel)
            }
            "fairy"-> {
                holder.pokemonType1Background.setCardBackgroundColor(ContextCompat.getColor(context,R.color.fairy))
                holder.pokemonBackground.setCardBackgroundColor(ContextCompat.getColor(context,R.color.fairy_bg))
                holder.pokemonIvType1.setImageResource(R.drawable.fairy)
            }
        }


        if (pokemonList[position].types.size > 1) {
            holder.pokemonType2.text = pokemonList[position].types[1].type.name
            holder.pokemonType2Background.isVisible = true
            when(pokemonList[position].types[1].type.name){
                "normal"-> {
                    holder.pokemonType2Background.setCardBackgroundColor(ContextCompat.getColor(context,R.color.normal))
                    holder.pokemonIvType2.setImageResource(R.drawable.normal)
                }
                "fire"-> {
                    holder.pokemonType2Background.setCardBackgroundColor(ContextCompat.getColor(context,R.color.fire))
                    holder.pokemonIvType2.setImageResource(R.drawable.fire)
                }
                "water"-> {
                    holder.pokemonType2Background.setCardBackgroundColor(ContextCompat.getColor(context,R.color.water))
                    holder.pokemonIvType2.setImageResource(R.drawable.water)
                }
                "grass"-> {
                    holder.pokemonType2Background.setCardBackgroundColor(ContextCompat.getColor(context,R.color.grass))
                    holder.pokemonIvType2.setImageResource(R.drawable.grass)
                }
                "electric"-> {
                    holder.pokemonType2Background.setCardBackgroundColor(ContextCompat.getColor(context,R.color.electric))
                    holder.pokemonIvType2.setImageResource(R.drawable.electric)
                }
                "ice"-> {
                    holder.pokemonType2Background.setCardBackgroundColor(ContextCompat.getColor(context,R.color.ice))
                    holder.pokemonIvType2.setImageResource(R.drawable.ice)
                }
                "fighting"-> {
                    holder.pokemonType2Background.setCardBackgroundColor(ContextCompat.getColor(context,R.color.fighting))
                    holder.pokemonIvType2.setImageResource(R.drawable.fighting)
                }
                "poison"-> {
                    holder.pokemonType2Background.setCardBackgroundColor(ContextCompat.getColor(context,R.color.poison))
                    holder.pokemonIvType2.setImageResource(R.drawable.poison)
                }
                "ground"-> {
                    holder.pokemonType2Background.setCardBackgroundColor(ContextCompat.getColor(context,R.color.ground))
                    holder.pokemonIvType2.setImageResource(R.drawable.ground)
                }
                "flying"-> {
                    holder.pokemonType2Background.setCardBackgroundColor(ContextCompat.getColor(context,R.color.flying))
                    holder.pokemonIvType2.setImageResource(R.drawable.flying)
                }
                "psychic"-> {
                    holder.pokemonType2Background.setCardBackgroundColor(ContextCompat.getColor(context,R.color.psychic))
                    holder.pokemonIvType2.setImageResource(R.drawable.psychic)
                }
                "bug"-> {
                    holder.pokemonType2Background.setCardBackgroundColor(ContextCompat.getColor(context,R.color.bug))
                    holder.pokemonIvType2.setImageResource(R.drawable.bug)
                }
                "rock"-> {
                    holder.pokemonType2Background.setCardBackgroundColor(ContextCompat.getColor(context,R.color.rock))
                    holder.pokemonIvType2.setImageResource(R.drawable.rock)
                }
                "ghost"-> {
                    holder.pokemonType2Background.setCardBackgroundColor(ContextCompat.getColor(context,R.color.ghost))
                    holder.pokemonIvType2.setImageResource(R.drawable.ghost)
                }
                "dragon"-> {
                    holder.pokemonType2Background.setCardBackgroundColor(ContextCompat.getColor(context,R.color.dragon))
                    holder.pokemonIvType2.setImageResource(R.drawable.dragon)
                }
                "dark"-> {
                    holder.pokemonType2Background.setCardBackgroundColor(ContextCompat.getColor(context,R.color.dark))
                    holder.pokemonIvType2.setImageResource(R.drawable.dark)
                }
                "steel"-> {
                    holder.pokemonType2Background.setCardBackgroundColor(ContextCompat.getColor(context,R.color.steel))
                    holder.pokemonIvType2.setImageResource(R.drawable.steel)
                }
                "fairy"-> {
                    holder.pokemonType2Background.setCardBackgroundColor(ContextCompat.getColor(context,R.color.fairy))
                    holder.pokemonIvType2.setImageResource(R.drawable.fairy)
                }
            }
        } else {
            holder.pokemonType2Background.isVisible = false
        }

        Picasso.get().load(pokemonList[position].sprites?.other?.officialArtwork?.frontDefault).into(holder.pokemonImage)

        holder.pokemonBackground.setOnClickListener {
            itemClickListener?.onItemClick(pokemonList[position])
        }
    }

    inner class PokemonViewHolder(binding: ItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        val pokemonName = binding.tvName
        val pokemonNumber = binding.tvNumber
        val pokemonType1 = binding.tvType1
        val pokemonType1Background = binding.cvBackgroundType1
        val pokemonType2 = binding.tvType2
        val pokemonType2Background = binding.cvBackgroundType2
        val pokemonBackground = binding.cvPokemonListItem
        val pokemonImage = binding.ivPokemon
        val pokemonIvType1 = binding.ivType1
        val pokemonIvType2 = binding.ivType2
    }

}