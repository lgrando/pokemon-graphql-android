package com.grando.pokemonapp.data.repositories

import com.grando.pokemonapp.domain.Pokemon

interface PokemonRepository {

    fun getCatchedPokemons(): List<Pokemon>

    fun catchPokemon(pokemon: Pokemon)

    suspend fun fetchPokemons(first: Int): List<Pokemon>

    suspend fun searchPokemon(name: String) : Pokemon
}