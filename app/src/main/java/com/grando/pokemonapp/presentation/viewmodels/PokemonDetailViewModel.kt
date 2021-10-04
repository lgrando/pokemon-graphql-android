package com.grando.pokemonapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.grando.pokemonapp.domain.Pokemon
import com.grando.pokemonapp.interactors.CatchPokemon
import com.grando.pokemonapp.interactors.GetCatchedPokemons

class PokemonDetailViewModel(
    private val catchPokemon: CatchPokemon,
    private val getCatchedPokemons: GetCatchedPokemons
) : ViewModel() {

    private val _catched = MutableLiveData<List<Pokemon>>()
    val catched: LiveData<List<Pokemon>> get() = _catched

    fun catch(pokemon: Pokemon) = catchPokemon(pokemon)

    fun getCatched() {
        val result = getCatchedPokemons()
        _catched.postValue(result)
    }
}