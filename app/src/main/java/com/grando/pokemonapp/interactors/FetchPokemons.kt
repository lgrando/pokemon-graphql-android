package com.grando.pokemonapp.interactors

import com.grando.pokemonapp.data.repositories.PokemonRepository
import com.grando.pokemonapp.domain.Pokemon

class FetchPokemons(private val repository: PokemonRepository) {

    suspend operator fun invoke(first: Int): List<Pokemon> = repository.fetchPokemons(first)
}