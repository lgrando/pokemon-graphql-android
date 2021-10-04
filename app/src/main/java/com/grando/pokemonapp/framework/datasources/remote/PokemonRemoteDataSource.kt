package com.grando.pokemonapp.framework.datasources.remote

import com.apollographql.apollo.coroutines.await
import com.grando.pokemonapp.data.datasources.PokemonDataSource
import com.grando.pokemonapp.domain.Pokemon
import com.grando.pokemonapp.framework.api.PokemonApiClient
import com.grando.pokemonapp.mappers.PokemonMapper

class PokemonRemoteDataSource : PokemonDataSource.Remote {

    override suspend fun fetchPokemons(first: Int): List<Pokemon> {
        return try {
            val pokemons = PokemonApiClient.getPokemons(first).await()
            val result = pokemons.data?.pokemons?.map { model ->
                model?.let { PokemonMapper.toEntity(model.fragments.pokemonModel) } ?: Pokemon()
            }
            result ?: listOf()
        } catch (e: Exception) {
            listOf()
        }
    }

    override suspend fun searchPokemon(name: String): Pokemon {
        return try {
            val pokemon = PokemonApiClient.getPokemon(name).await()
            pokemon.data?.pokemon?.fragments?.pokemonModel?.let {
                PokemonMapper.toEntity(it)
            } ?: Pokemon()
        } catch (e: Exception) {
            Pokemon()
        }
    }
}