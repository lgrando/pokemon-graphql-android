package com.grando.pokemonapp.framework.api

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.ApolloQueryCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.grando.pokemonapp.BuildConfig
import com.grando.pokemonapp.GetPokemonQuery
import com.grando.pokemonapp.GetPokemonsQuery

object PokemonApiClient {

    private fun apolloClient(): ApolloClient =
        ApolloClient.builder().serverUrl(BuildConfig.BASE_URL).build()

    fun getPokemons(first: Int): ApolloQueryCall<GetPokemonsQuery.Data> =
        apolloClient().query(GetPokemonsQuery(first))

    fun getPokemon(name: String): ApolloQueryCall<GetPokemonQuery.Data> =
        apolloClient().query(GetPokemonQuery(name))
}