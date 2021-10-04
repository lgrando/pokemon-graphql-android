package com.grando.pokemonapp.framework.datasources.local

import com.grando.pokemonapp.domain.Pokemon

private const val DATABASE_NAME = "catched_pokemons"

object PokemonsDao : PaperDao<ArrayList<Pokemon>>(DATABASE_NAME)