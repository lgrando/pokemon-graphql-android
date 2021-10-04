package com.grando.pokemonapp.presentation

import com.grando.pokemonapp.PokemonTestUtils
import com.grando.pokemonapp.presentation.utils.extensions.formatHeight
import com.grando.pokemonapp.presentation.utils.extensions.formatName
import com.grando.pokemonapp.presentation.utils.extensions.formatWeight
import org.junit.Test

class ExtensionsUnitTest {

    @Test
    fun `Format name, append pokemon number and name with # prefix, then assert the name is formatted`() {
        val expected = "#000 Name"
        val actual = PokemonTestUtils.getPokemon().formatName()
        assert(expected == actual)
    }

    @Test
    fun `Format weight, append pokemon minimum and maximum weight, then assert the weight is formatted`() {
        val expected = "Min: 0\nMax: 0"
        val actual = PokemonTestUtils.getPokemon().formatWeight()
        assert(expected == actual)
    }

    @Test
    fun `Format height, append pokemon minimum and maximum height, then assert the height is formatted`() {
        val expected = "Min: 0\nMax: 0"
        val actual = PokemonTestUtils.getPokemon().formatHeight()
        assert(expected == actual)
    }
}