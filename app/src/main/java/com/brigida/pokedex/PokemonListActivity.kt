package com.brigida.pokedex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.brigida.pokedex.databinding.ActivityPokemonListBinding
import com.brigida.pokedex.model.Pokemon
import com.brigida.pokedex.model.Pokemons
import com.brigida.pokedex.network.ApiInterface
import com.brigida.pokedex.network.NetworkUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class PokemonListActivity : AppCompatActivity(), PokemonAdapter.OnItemClickListener {

    private lateinit var binding: ActivityPokemonListBinding
    private lateinit var pokemonAdapter: PokemonAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            tvGeneration.text = intent.getStringExtra("generation")
            recyclerView = rvPokemonList
        }
        fetchData()
    }


    private fun fetchData() {
        val limit = intent.getIntExtra("limit", 151)
        val offset = intent.getIntExtra("offset", 0)

        val apiService = NetworkUtils.getRetrofitInstance().create(ApiInterface::class.java)

        lifecycleScope.launch(Dispatchers.Main) {
            binding.apply {
                animationPokeball.visibility = View.VISIBLE
                animationLoading.visibility = View.VISIBLE
            }
            try {
                val response: Response<Pokemons> = apiService.listPokemons(offset, limit)
                if (response.isSuccessful) {
                    val pokemons = response.body()?.results ?: emptyList()
                    val detailedPokemonList = mutableListOf<Pokemon>()

                    for (pokemonResult in pokemons) {
                        val number = pokemonResult.url
                            .replace("https://pokeapi.co/api/v2/pokemon/", "")
                            .replace("/", "")
                            .toInt()

                        val detailedPokemonResponse = apiService.getPokemon(number)
                        if (detailedPokemonResponse.isSuccessful) {
                            val detailedPokemon = detailedPokemonResponse.body()
                            detailedPokemon?.let { detailedPokemonList.add(it) }
                        }
                    }

                    pokemonAdapter = PokemonAdapter(this@PokemonListActivity, detailedPokemonList)

                    recyclerView.layoutManager = LinearLayoutManager(this@PokemonListActivity)
                    recyclerView.setHasFixedSize(true)
                    recyclerView.adapter = pokemonAdapter
                    // Configurar o listener após obter o adapter
                    pokemonAdapter.setOnItemClickListener(this@PokemonListActivity)
                }
            } catch (e: Exception) {
                Log.e("Error", e.message ?: "Unknown error")
            }
            finally {
                binding.apply {
                    animationPokeball.visibility = View.GONE
                    animationLoading.visibility = View.GONE
                    ivTitle.isVisible = true
                    tvGeneration.isVisible = true
                }

            }
        }
    }

    // Implementar o método da interface
    override fun onItemClick(pokemon: Pokemon) {
        // Lógica para tratar o clique do item
        // Exemplo: abrir detalhes do Pokémon
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("pokemonNumber", pokemon.id)
        startActivity(intent)
    }
}