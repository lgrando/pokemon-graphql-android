package com.grando.pokemonapp.interactors

import com.grando.pokemonapp.PokemonTestUtils
import com.grando.pokemonapp.data.repositories.PokemonRepository
import com.nhaarman.mockitokotlin2.*
import org.junit.Before
import org.junit.Test

class CatchPokemonTest {

    private lateinit var repositoryMock: PokemonRepository
    private lateinit var interactor: CatchPokemon

    @Before
    fun setUp() {
        repositoryMock = mock()
        interactor = CatchPokemon(repositoryMock)
    }

    @Test
    fun catchPokemon() {
        val pokemon = PokemonTestUtils.getPokemon()

        doNothing().`when`(repositoryMock).catchPokemon(pokemon)

        interactor.invoke(pokemon)

        verify(repositoryMock, times(1)).catchPokemon(pokemon)
    }
}