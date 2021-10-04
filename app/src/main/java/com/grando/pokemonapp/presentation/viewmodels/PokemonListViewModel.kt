package com.grando.pokemonapp.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.grando.pokemonapp.domain.Pokemon
import com.grando.pokemonapp.framework.utils.request
import com.grando.pokemonapp.interactors.FetchPokemons
import com.grando.pokemonapp.interactors.GetCatchedPokemons
import com.grando.pokemonapp.interactors.SearchPokemon

class PokemonListViewModel(
    private val fetchPokemons: FetchPokemons,
    private val searchPokemon: SearchPokemon,
    private val getCatchedPokemons: GetCatchedPokemons
) : ViewModel() {

    var currentPage = 10

    private val _catched = MutableLiveData<List<Pokemon>>()
    val catched: LiveData<List<Pokemon>> get() = _catched

    init {
        getCatched()
    }

    fun getCatched() {
        val result = getCatchedPokemons()
        _catched.postValue(result)
    }

    fun getAllPokemons() = request {
        fetchPokemons(currentPage)
    }

    fun searchPokemonByName(name: String) = request {
        searchPokemon(name)
    }

    fun updateIfCatched(pokemon: Pokemon): Pokemon =
        pokemon.apply {
            isCatched = _catched.value?.find { it.id == id } != null
        }
}