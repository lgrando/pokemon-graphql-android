package com.grando.pokemonapp.interactors

import com.grando.pokemonapp.PokemonTestUtils
import com.grando.pokemonapp.data.repositories.PokemonRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchPokemonTest {

    private lateinit var repositoryMock: PokemonRepository
    private lateinit var interactor: SearchPokemon

    @Before
    fun setUp() {
        repositoryMock = mock()
        interactor = SearchPokemon(repositoryMock)
    }

    @Test
    fun searchPokemon() = runBlockingTest {
        val name = "name"
        val expected = PokemonTestUtils.getPokemon()

        whenever(repositoryMock.searchPokemon(name)).thenReturn(expected)

        val actual = interactor.invoke(name)

        assert(expected == actual)
    }
}