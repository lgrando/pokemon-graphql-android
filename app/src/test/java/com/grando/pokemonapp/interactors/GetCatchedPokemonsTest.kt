package com.grando.pokemonapp.interactors

import com.grando.pokemonapp.PokemonTestUtils
import com.grando.pokemonapp.data.repositories.PokemonRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test

class GetCatchedPokemonsTest {

    private lateinit var repositoryMock: PokemonRepository
    private lateinit var interactor: GetCatchedPokemons

    @Before
    fun setUp() {
        repositoryMock = mock()
        interactor = GetCatchedPokemons(repositoryMock)
    }

    @Test
    fun getCatchedPokemons() {
        val expected = PokemonTestUtils.getPokemons()

        whenever(repositoryMock.getCatchedPokemons()).thenReturn(expected)

        val actual = interactor.invoke()

        assert(expected == actual)
    }
}