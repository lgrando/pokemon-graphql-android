package com.grando.pokemonapp.data.repositories

import com.grando.pokemonapp.data.datasources.PokemonDataSource
import com.grando.pokemonapp.domain.Pokemon

class PokemonRepositoryImpl(
    private val remote: PokemonDataSource.Remote,
    private val local: PokemonDataSource.Local) : PokemonRepository {

    override fun getCatchedPokemons(): List<Pokemon> = local.getCatchedPokemons()

    override fun catchPokemon(pokemon: Pokemon) = local.catchPokemon(pokemon)

    override suspend fun fetchPokemons(first: Int): List<Pokemon> = remote.fetchPokemons(first)

    override suspend fun searchPokemon(name: String): Pokemon = remote.searchPokemon(name)
}