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
class FetchPokemonsTest {

    private lateinit var repositoryMock: PokemonRepository
    private lateinit var interactor: FetchPokemons

    @Before
    fun setUp() {
        repositoryMock = mock()
        interactor = FetchPokemons(repositoryMock)
    }

    @Test
    fun fetchAll() = runBlockingTest {
        val page = 1
        val expected = PokemonTestUtils.getPokemons()

        whenever(repositoryMock.fetchPokemons(page)).thenReturn(expected)

        val actual = interactor.invoke(page)

        assert(expected == actual)
    }
}