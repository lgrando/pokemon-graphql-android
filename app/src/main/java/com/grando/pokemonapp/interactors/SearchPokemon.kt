package com.grando.pokemonapp.interactors

import com.grando.pokemonapp.data.repositories.PokemonRepository
import com.grando.pokemonapp.domain.Pokemon

class SearchPokemon(private val repository: PokemonRepository) {

    suspend operator fun invoke(name: String): Pokemon = repository.searchPokemon(name)
}