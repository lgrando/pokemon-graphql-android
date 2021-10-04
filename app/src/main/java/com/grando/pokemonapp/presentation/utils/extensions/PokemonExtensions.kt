package com.grando.pokemonapp.presentation.utils.extensions

import com.grando.pokemonapp.domain.Pokemon

fun Pokemon.formatName(): String = "#$number $name"

fun Pokemon.formatWeight(): String = "Min: ${weight.minimum}\nMax: ${weight.maximum}"

fun Pokemon.formatHeight(): String = "Min: ${height.minimum}\nMax: ${height.maximum}"