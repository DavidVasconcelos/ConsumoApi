package br.com.fiap.consumoapi.api

import br.com.fiap.consumoapi.model.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {

    @GET("/api/v2/pokemon/{number}")
    fun search(@Path("number")number: String) : Call<Pokemon>
}