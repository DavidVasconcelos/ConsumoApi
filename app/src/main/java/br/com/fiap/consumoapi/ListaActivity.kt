package br.com.fiap.consumoapi

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import br.com.fiap.consumoapi.model.Pokemon
import br.com.fiap.consumoapi.model.Sprites
import br.com.heiderlopes.consumoapi.ListaAdapter
import kotlinx.android.synthetic.main.activity_lista.*

class ListaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        rvPokemons.adapter = ListaAdapter(listOf(
            Pokemon("Charmander",
                Sprites("https://assets.pokemon.com/assets/cms2/img/pokedex/full/004.png")
            ),
            Pokemon("Squirtle",
                Sprites("https://assets.pokemon.com/assets/cms2/img/pokedex/full/007.png")),
            Pokemon("Bulbassaur",
                Sprites("https://assets.pokemon.com/assets/cms2/img/pokedex/full/001.png"))
        ), this, {})
        rvPokemons.layoutManager = LinearLayoutManager(this)
    }
}
