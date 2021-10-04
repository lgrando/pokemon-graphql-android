package com.grando.pokemonapp.core

import android.app.Application
import com.grando.pokemonapp.di.getModules
import io.paperdb.Paper
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PokemonApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PokemonApplication)
            modules(getModules())
        }

        Paper.init(this)
    }
}