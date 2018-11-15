package br.com.fiap.consumoapi

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.fiap.consumoapi.api.PokemonApi
import br.com.fiap.consumoapi.model.Pokemon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient




class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        btSearch.setOnClickListener {
            search()
        }
    }

    private fun search() {
        val okHttp = OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp)
            .build()

        val pokeApi = retrofit.create(PokemonApi::class.java)

        pokeApi.search(
            inputNumeroPokemon.text.toString()
        ).enqueue(object : Callback<Pokemon> {
            override fun onFailure(call: Call<Pokemon>?, t: Throwable?) {
                Toast.makeText(this@SearchActivity,
                    t?.message,
                    Toast.LENGTH_LONG)
                    .show()
            }

            override fun onResponse(call: Call<Pokemon>?, response: Response<Pokemon>?) {
                if(response?.isSuccessful == true) {

                    val pokemon = response.body()
                    tvPokemon.text = pokemon?.name

                    Picasso.get()
                        .load(pokemon?.sprites?.frontDefault)
                        .placeholder(R.drawable.searching)
                        .error(R.drawable.notfound)
                        .into(ivPokemon)

                } else {

                    tvPokemon.text = "Não encontrado"

                    Picasso.get()
                        .load(R.drawable.notfound)
                        .into(ivPokemon)
                }

            }
        })
    }
}
