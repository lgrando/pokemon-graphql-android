package com.grando.pokemonapp.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pokemon(
    val id: String = "",
    val number: String = "",
    val name: String = "",
    val image: String = "",
    val weight: Dimension = Dimension(),
    val height: Dimension = Dimension(),
    val types: List<TypeEnum> = listOf(),
    val fast: List<Attack> = listOf(),
    val special: List<Attack> = listOf(),
    val evolutions: List<Pokemon> = listOf(),
    var isCatched: Boolean = false
) : Parcelable
