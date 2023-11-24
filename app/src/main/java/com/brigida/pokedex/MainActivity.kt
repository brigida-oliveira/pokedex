package com.brigida.pokedex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.brigida.pokedex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            cvGen1.setOnClickListener {
                val intent = Intent(this@MainActivity, PokemonListActivity::class.java)
                intent.putExtra("limit", 151)
                intent.putExtra("offset", 0)
                intent.putExtra("generation", "Generation I")
                startActivity(intent)
            }

            cvGen2.setOnClickListener {
                val intent = Intent(this@MainActivity, PokemonListActivity::class.java)
                intent.putExtra("limit", 100)
                intent.putExtra("offset", 151)
                intent.putExtra("generation", "Generation II")
                startActivity(intent)
            }

            cvGen3.setOnClickListener {
                val intent = Intent(this@MainActivity, PokemonListActivity::class.java)
                intent.putExtra("limit", 135)
                intent.putExtra("offset", 251)
                intent.putExtra("generation", "Generation III")
                startActivity(intent)
            }

            cvGen4.setOnClickListener {
                val intent = Intent(this@MainActivity, PokemonListActivity::class.java)
                intent.putExtra("limit", 107)
                intent.putExtra("offset", 386)
                intent.putExtra("generation", "Generation IV")
                startActivity(intent)
            }

            cvGen5.setOnClickListener {
                val intent = Intent(this@MainActivity, PokemonListActivity::class.java)
                intent.putExtra("limit", 156)
                intent.putExtra("offset", 494)
                intent.putExtra("generation", "Generation V")
                startActivity(intent)
            }

            cvGen6.setOnClickListener {
                val intent = Intent(this@MainActivity, PokemonListActivity::class.java)
                intent.putExtra("limit", 72)
                intent.putExtra("offset", 649)
                intent.putExtra("generation", "Generation VI")
                startActivity(intent)
            }

            cvGen7.setOnClickListener {
                val intent = Intent(this@MainActivity, PokemonListActivity::class.java)
                intent.putExtra("limit", 88)
                intent.putExtra("offset", 721)
                intent.putExtra("generation", "Generation VII")
                startActivity(intent)
            }

            cvGen8.setOnClickListener {
                val intent = Intent(this@MainActivity, PokemonListActivity::class.java)
                intent.putExtra("limit", 96)
                intent.putExtra("offset", 809)
                intent.putExtra("generation", "Generation VIII")
                startActivity(intent)
            }

            cvGen9.setOnClickListener {
                val intent = Intent(this@MainActivity, PokemonListActivity::class.java)
                intent.putExtra("limit", 150)
                intent.putExtra("offset", 905)
                intent.putExtra("generation", "Generation IX")
                startActivity(intent)
            }
        }
    }
}