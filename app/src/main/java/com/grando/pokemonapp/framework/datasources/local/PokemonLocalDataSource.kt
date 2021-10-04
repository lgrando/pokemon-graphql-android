package com.grando.pokemonapp.framework.datasources.local

import com.grando.pokemonapp.data.datasources.PokemonDataSource
import com.grando.pokemonapp.domain.Pokemon

class PokemonLocalDataSource(private val dao: AbstractDao<ArrayList<Pokemon>>) :
    PokemonDataSource.Local {

    override fun getCatchedPokemons(): ArrayList<Pokemon> = dao.getData() ?: arrayListOf()

    override fun catchPokemon(pokemon: Pokemon) {
        val list = getCatchedPokemons()
        list.add(pokemon)
        dao.setData(list)
    }
}