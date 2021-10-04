package com.grando.pokemonapp.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Attack(
    val name: String = "",
    val type: TypeEnum = TypeEnum.DEFAULT,
    val damage: Int = 0
) : Parcelable