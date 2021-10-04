package com.grando.pokemonapp.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.grando.pokemonapp.CoroutinesTestRule
import com.grando.pokemonapp.PokemonTestUtils
import com.grando.pokemonapp.domain.Pokemon
import com.grando.pokemonapp.framework.utils.State
import com.grando.pokemonapp.getOrAwaitValue
import com.grando.pokemonapp.interactors.FetchPokemons
import com.grando.pokemonapp.interactors.GetCatchedPokemons
import com.grando.pokemonapp.interactors.SearchPokemon
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class PokemonListViewModelTest {

    private lateinit var fetchPokemonsMock: FetchPokemons
    private lateinit var searchPokemonMock: SearchPokemon
    private lateinit var getCatchedPokemonsMock: GetCatchedPokemons
    private lateinit var viewModel: PokemonListViewModel

    @JvmField
    @Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @Before
    fun setUp() {
        fetchPokemonsMock = mock(FetchPokemons::class.java)
        searchPokemonMock = mock(SearchPokemon::class.java)
        getCatchedPokemonsMock = mock(GetCatchedPokemons::class.java)
        viewModel =
            PokemonListViewModel(fetchPokemonsMock, searchPokemonMock, getCatchedPokemonsMock)
    }

    @Test
    fun fetchPokemons() = runBlockingTest {
        val page = 10
        val pokemons = PokemonTestUtils.getPokemons()
        val expectedDataState = State.DataState(pokemons)
        val observerMock: Observer<State<List<Pokemon>>> = mock()
        whenever(fetchPokemonsMock.invoke(page)).thenReturn(pokemons)

        val actual = viewModel.getAllPokemons()
        actual.observeForever(observerMock)

        verify(fetchPokemonsMock, times(1)).invoke(page)
        verify(observerMock, times(1)).onChanged(expectedDataState)
        assert(expectedDataState == actual.getOrAwaitValue())
    }

    @Test
    fun searchPokemon() = runBlockingTest {
        val name = "name"
        val pokemon = PokemonTestUtils.getPokemon()
        val expectedDataState = State.DataState(pokemon)
        val observerMock: Observer<State<Pokemon>> = mock()
        whenever(searchPokemonMock.invoke(name)).thenReturn(pokemon)

        val actual = viewModel.searchPokemonByName(name)
        actual.observeForever(observerMock)

        verify(searchPokemonMock, times(1)).invoke(name)
        verify(observerMock, times(1)).onChanged(expectedDataState)
        assert(expectedDataState == actual.getOrAwaitValue())
    }

    @Test
    fun getCatchedPokemons() {
        val expected = PokemonTestUtils.getPokemons()

        whenever(getCatchedPokemonsMock.invoke()).thenReturn(expected)

        viewModel.getCatched()

        assert(expected == viewModel.catched.value)
    }
}