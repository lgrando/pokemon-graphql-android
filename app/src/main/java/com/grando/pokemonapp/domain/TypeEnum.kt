package com.grando.pokemonapp.domain

import android.graphics.Color
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class TypeEnum(val label: String, val color: Int) : Parcelable {
    BUG("Bug", Color.parseColor("#a8b820")),
    DARK("Dark", Color.parseColor("#705848")),
    DRAGON("Dragon", Color.parseColor("#7038f8")),
    ELECTRIC("Electric", Color.parseColor("#f8dc71")),
    FAIRY("Fairy", Color.parseColor("#dea5de")),
    FIGHTING("Fighting", Color.parseColor("#c0332b")),
    FIRE("Fire", Color.parseColor("#f08030")),
    FLYING("Flying", Color.parseColor("#a58ee8")),
    GHOST("Ghost", Color.parseColor("#705898")),
    GRASS("Grass", Color.parseColor("#78c850")),
    GROUND("Ground", Color.parseColor("#debf68")),
    ICE("Ice", Color.parseColor("#98d8d8")),
    NORMAL("Normal", Color.parseColor("#a8a879")),
    POISON("Poison", Color.parseColor("#9f409f")),
    PSYCHIC("Psychic", Color.parseColor("#ef5d88")),
    ROCK("Rock", Color.parseColor("#b8a038")),
    STEEL("Steel", Color.parseColor("#b8b8d0")),
    WATER("Water", Color.parseColor("#6890f0")),
    DEFAULT("--", Color.parseColor("#cccccc"))
}