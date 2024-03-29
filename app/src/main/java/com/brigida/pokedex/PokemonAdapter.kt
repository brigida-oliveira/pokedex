package com.brigida.pokedex

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
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
            "#00${pokemonList[position].id}".also { holder.pokemonNumber.text = it }
        } else if (pokemonList[position].id < 100) {
            "#0${pokemonList[position].id}".also { holder.pokemonNumber.text = it }
        } else {
            "#${pokemonList[position].id}".also { holder.pokemonNumber.text = it }
        }

        holder.pokemonType1.text = pokemonList[position].types[0].type.name
        when(pokemonList[position].types[0].type.name){
            "normal"-> changeType1ColorAndBackground(holder.pokemonType1Background, holder.pokemonBackground, holder.pokemonIvType1, R.color.normal, R.drawable.normal, R.color.normal_bg)
            "fire"-> changeType1ColorAndBackground(holder.pokemonType1Background, holder.pokemonBackground, holder.pokemonIvType1, R.color.fire, R.drawable.fire, R.color.fire_bg)
            "water"-> changeType1ColorAndBackground(holder.pokemonType1Background, holder.pokemonBackground, holder.pokemonIvType1, R.color.water, R.drawable.water, R.color.water_bg)
            "grass"-> changeType1ColorAndBackground(holder.pokemonType1Background, holder.pokemonBackground, holder.pokemonIvType1, R.color.grass, R.drawable.grass, R.color.grass_bg)
            "electric"-> changeType1ColorAndBackground(holder.pokemonType1Background, holder.pokemonBackground, holder.pokemonIvType1, R.color.electric, R.drawable.electric, R.color.electric_bg)
            "ice"-> changeType1ColorAndBackground(holder.pokemonType1Background, holder.pokemonBackground, holder.pokemonIvType1, R.color.ice, R.drawable.ice, R.color.ice_bg)
            "fighting"-> changeType1ColorAndBackground(holder.pokemonType1Background, holder.pokemonBackground, holder.pokemonIvType1, R.color.fighting, R.drawable.fighting, R.color.fighting_bg)
            "poison"-> changeType1ColorAndBackground(holder.pokemonType1Background, holder.pokemonBackground, holder.pokemonIvType1, R.color.poison, R.drawable.poison, R.color.poison_bg)
            "ground"-> changeType1ColorAndBackground(holder.pokemonType1Background, holder.pokemonBackground, holder.pokemonIvType1, R.color.ground, R.drawable.ground, R.color.ground_bg)
            "flying"-> changeType1ColorAndBackground(holder.pokemonType1Background, holder.pokemonBackground, holder.pokemonIvType1, R.color.flying, R.drawable.flying, R.color.flying_bg)
            "psychic"-> changeType1ColorAndBackground(holder.pokemonType1Background, holder.pokemonBackground, holder.pokemonIvType1, R.color.psychic, R.drawable.psychic, R.color.psychic_bg)
            "bug"-> changeType1ColorAndBackground(holder.pokemonType1Background, holder.pokemonBackground, holder.pokemonIvType1, R.color.bug, R.drawable.bug, R.color.bug_bg)
            "rock"-> changeType1ColorAndBackground(holder.pokemonType1Background, holder.pokemonBackground, holder.pokemonIvType1, R.color.rock, R.drawable.rock, R.color.rock_bg)
            "ghost"-> changeType1ColorAndBackground(holder.pokemonType1Background, holder.pokemonBackground, holder.pokemonIvType1, R.color.ghost, R.drawable.ghost, R.color.ghost_bg)
            "dragon"-> changeType1ColorAndBackground(holder.pokemonType1Background, holder.pokemonBackground, holder.pokemonIvType1, R.color.dragon, R.drawable.dragon, R.color.dragon_bg)
            "dark"-> changeType1ColorAndBackground(holder.pokemonType1Background, holder.pokemonBackground, holder.pokemonIvType1, R.color.dark, R.drawable.dark, R.color.dark_bg)
            "steel"-> changeType1ColorAndBackground(holder.pokemonType1Background, holder.pokemonBackground, holder.pokemonIvType1, R.color.steel, R.drawable.steel, R.color.steel_bg)
            "fairy"-> changeType1ColorAndBackground(holder.pokemonType1Background, holder.pokemonBackground, holder.pokemonIvType1, R.color.fairy, R.drawable.fairy, R.color.fairy_bg)
        }


        if (pokemonList[position].types.size > 1) {
            holder.pokemonType2.text = pokemonList[position].types[1].type.name
            holder.pokemonType2Background.isVisible = true
            when(pokemonList[position].types[1].type.name){
                "normal"-> changeType2Color(holder.pokemonType2Background, holder.pokemonIvType2, R.color.normal, R.drawable.normal)
                "fire"-> changeType2Color(holder.pokemonType2Background, holder.pokemonIvType2, R.color.fire, R.drawable.fire)
                "water"-> changeType2Color(holder.pokemonType2Background, holder.pokemonIvType2, R.color.water, R.drawable.water)
                "grass"-> changeType2Color(holder.pokemonType2Background, holder.pokemonIvType2, R.color.grass, R.drawable.grass)
                "electric"-> changeType2Color(holder.pokemonType2Background, holder.pokemonIvType2, R.color.electric, R.drawable.electric)
                "ice"-> changeType2Color(holder.pokemonType2Background, holder.pokemonIvType2, R.color.ice, R.drawable.ice)
                "fighting"-> changeType2Color(holder.pokemonType2Background, holder.pokemonIvType2, R.color.fighting, R.drawable.fighting)
                "poison"-> changeType2Color(holder.pokemonType2Background, holder.pokemonIvType2, R.color.poison, R.drawable.poison)
                "ground"-> changeType2Color(holder.pokemonType2Background, holder.pokemonIvType2, R.color.ground, R.drawable.ground)
                "flying"-> changeType2Color(holder.pokemonType2Background, holder.pokemonIvType2, R.color.flying, R.drawable.flying)
                "psychic"-> changeType2Color(holder.pokemonType2Background, holder.pokemonIvType2, R.color.psychic, R.drawable.psychic)
                "bug"-> changeType2Color(holder.pokemonType2Background, holder.pokemonIvType2, R.color.bug, R.drawable.bug)
                "rock"-> changeType2Color(holder.pokemonType2Background, holder.pokemonIvType2, R.color.rock, R.drawable.rock)
                "ghost"-> changeType2Color(holder.pokemonType2Background, holder.pokemonIvType2, R.color.ghost, R.drawable.ghost)
                "dragon"-> changeType2Color(holder.pokemonType2Background, holder.pokemonIvType2, R.color.dragon, R.drawable.dragon)
                "dark"-> changeType2Color(holder.pokemonType2Background, holder.pokemonIvType2, R.color.dark, R.drawable.dark)
                "steel"-> changeType2Color(holder.pokemonType2Background, holder.pokemonIvType2, R.color.steel, R.drawable.steel)
                "fairy"-> changeType2Color(holder.pokemonType2Background, holder.pokemonIvType2, R.color.fairy, R.drawable.fairy)
            }
        } else {
            holder.pokemonType2Background.isVisible = false
        }

        Picasso.get().load(pokemonList[position].sprites.other.officialArtwork.frontDefault).into(holder.pokemonImage)

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

    private fun changeType1ColorAndBackground(cvType: CardView, cvCard: CardView, ivType: ImageView, color: Int, image: Int, color2: Int) {
        cvType.setCardBackgroundColor(ContextCompat.getColor(context,color))
        cvCard.setCardBackgroundColor(ContextCompat.getColor(context,color2))
        ivType.setImageResource(image)
    }

    private fun changeType2Color(cvType: CardView, ivType: ImageView, color: Int, image: Int) {
        cvType.setCardBackgroundColor(ContextCompat.getColor(context,color))
        ivType.setImageResource(image)
    }
}