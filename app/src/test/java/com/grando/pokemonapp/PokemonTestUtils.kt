package com.grando.pokemonapp

import com.grando.pokemonapp.domain.Dimension
import com.grando.pokemonapp.domain.Pokemon

object PokemonTestUtils {

    fun getPokemon() = Pokemon(
        id = "id",
        number = "000",
        name = "Name",
        image = "Image",
        weight = getDimension(),
        height = getDimension()
    )

    private fun getDimension() = Dimension(minimum = "0", maximum = "0")

    fun getPokemons() = listOf(getPokemon())
}