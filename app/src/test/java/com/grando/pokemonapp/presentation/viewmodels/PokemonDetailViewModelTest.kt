package com.grando.pokemonapp.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.grando.pokemonapp.CoroutinesTestRule
import com.grando.pokemonapp.PokemonTestUtils
import com.grando.pokemonapp.interactors.CatchPokemon
import com.grando.pokemonapp.interactors.GetCatchedPokemons
import com.nhaarman.mockitokotlin2.doNothing
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class PokemonDetailViewModelTest {

    private lateinit var catchPokemonMock: CatchPokemon
    private lateinit var getCatchedPokemonsMock: GetCatchedPokemons
    private lateinit var viewModel: PokemonDetailViewModel

    @JvmField
    @Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @Before
    fun setUp() {
        catchPokemonMock = Mockito.mock(CatchPokemon::class.java)
        getCatchedPokemonsMock = Mockito.mock(GetCatchedPokemons::class.java)
        viewModel =
            PokemonDetailViewModel(catchPokemonMock, getCatchedPokemonsMock)
    }

    @Test
    fun catchPokemon() {
        val pokemon = PokemonTestUtils.getPokemon()

        doNothing().`when`(catchPokemonMock).invoke(pokemon)

        viewModel.catch(pokemon)

        verify(catchPokemonMock, times(1)).invoke(pokemon)
    }

    @Test
    fun getCatchedPokemons() {
        val expected = PokemonTestUtils.getPokemons()

        whenever(getCatchedPokemonsMock.invoke()).thenReturn(expected)

        viewModel.getCatched()

        assert(expected == viewModel.catched.value)
    }
}