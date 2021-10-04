package com.grando.pokemonapp.data.datasources

import com.grando.pokemonapp.domain.Pokemon

interface PokemonDataSource {

    interface Local {
        fun getCatchedPokemons(): List<Pokemon>

        fun catchPokemon(pokemon: Pokemon)
    }

    interface Remote {
        suspend fun fetchPokemons(first: Int): List<Pokemon>

        suspend fun searchPokemon(name: String): Pokemon
    }
}