package com.grando.pokemonapp.di

import com.grando.pokemonapp.data.datasources.PokemonDataSource
import com.grando.pokemonapp.data.repositories.PokemonRepository
import com.grando.pokemonapp.data.repositories.PokemonRepositoryImpl
import com.grando.pokemonapp.framework.datasources.local.PokemonLocalDataSource
import com.grando.pokemonapp.framework.datasources.local.PokemonsDao
import com.grando.pokemonapp.framework.datasources.remote.PokemonRemoteDataSource
import com.grando.pokemonapp.interactors.CatchPokemon
import com.grando.pokemonapp.interactors.FetchPokemons
import com.grando.pokemonapp.interactors.GetCatchedPokemons
import com.grando.pokemonapp.interactors.SearchPokemon
import com.grando.pokemonapp.presentation.viewmodels.PokemonDetailViewModel
import com.grando.pokemonapp.presentation.viewmodels.PokemonListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

private val modules: Module = module {

    // Data Sources
    factory<PokemonDataSource.Remote> { PokemonRemoteDataSource() }
    factory<PokemonDataSource.Local> { PokemonLocalDataSource(dao = PokemonsDao) }

    // Repositories
    factory<PokemonRepository> { PokemonRepositoryImpl(remote = get(), local = get()) }

    // Usecases
    factory { FetchPokemons(repository = get()) }
    factory { SearchPokemon(repository = get()) }
    factory { GetCatchedPokemons(repository = get()) }
    factory { CatchPokemon(repository = get()) }

    // ViewModels
    viewModel {
        PokemonListViewModel(
            fetchPokemons = get(),
            searchPokemon = get(),
            getCatchedPokemons = get()
        )
    }
    viewModel { PokemonDetailViewModel(catchPokemon = get(), getCatchedPokemons = get()) }
}

fun getModules(): List<Module> = listOf(modules)
