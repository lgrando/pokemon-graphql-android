package com.grando.pokemonapp.interactors

import com.grando.pokemonapp.data.repositories.PokemonRepository
import com.grando.pokemonapp.domain.Pokemon

class CatchPokemon(private val repository: PokemonRepository) {

    operator fun invoke(pokemon: Pokemon) = repository.catchPokemon(pokemon)
}