package com.grando.pokemonapp.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.grando.pokemonapp.CoroutinesTestRule
import com.grando.pokemonapp.PokemonTestUtils
import com.grando.pokemonapp.data.datasources.PokemonDataSource
import com.grando.pokemonapp.data.repositories.PokemonRepositoryImpl
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PokemonRepositoryTest {

    private lateinit var remoteDataSourceMock: PokemonDataSource.Remote
    private lateinit var localDataSourceMock: PokemonDataSource.Local
    private lateinit var repository: PokemonRepositoryImpl

    @JvmField
    @Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @Before
    fun setUp() {
        remoteDataSourceMock = mock()
        localDataSourceMock = mock()
        repository = PokemonRepositoryImpl(remoteDataSourceMock, localDataSourceMock)
    }

    @Test
    fun fetchPokemons() = runBlockingTest {
        val page = 1
        val expected = PokemonTestUtils.getPokemons()

        whenever(remoteDataSourceMock.fetchPokemons(page)).thenReturn(expected)

        val actual = repository.fetchPokemons(page)

        assert(expected == actual)
    }

    @Test
    fun searchPokemon() = runBlockingTest {
        val name = "name"
        val expected = PokemonTestUtils.getPokemon()

        whenever(remoteDataSourceMock.searchPokemon(name)).thenReturn(expected)

        val actual = repository.searchPokemon(name)

        assert(expected == actual)
    }

    @Test
    fun getCatchedPokemons() {
        val expected = PokemonTestUtils.getPokemons()

        whenever(localDataSourceMock.getCatchedPokemons()).thenReturn(expected)

        val actual = repository.getCatchedPokemons()

        assert(expected == actual)
    }

    @Test
    fun catchPokemon() {
        val pokemon = PokemonTestUtils.getPokemon()

        doNothing().`when`(localDataSourceMock).catchPokemon(pokemon)

        repository.catchPokemon(pokemon)

        verify(localDataSourceMock, times(1)).catchPokemon(pokemon)
    }
}