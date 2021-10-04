package com.grando.pokemonapp.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Dimension(
    val minimum: String = "",
    val maximum: String = ""
) : Parcelable