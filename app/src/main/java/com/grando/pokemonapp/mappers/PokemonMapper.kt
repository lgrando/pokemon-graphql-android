package com.grando.pokemonapp.mappers

import com.grando.pokemonapp.domain.Attack
import com.grando.pokemonapp.domain.Dimension
import com.grando.pokemonapp.domain.Pokemon
import com.grando.pokemonapp.domain.TypeEnum
import com.grando.pokemonapp.fragment.PokemonModel

object PokemonMapper {

    fun toEntity(model: PokemonModel) = Pokemon(
        id = model.id,
        number = model.number ?: "",
        name = model.name ?: "",
        image = model.image ?: "",
        weight = Dimension(
            minimum = model.weight?.minimum ?: "",
            maximum = model.weight?.maximum ?: "",
        ),
        height = Dimension(
            minimum = model.height?.minimum ?: "",
            maximum = model.height?.maximum ?: "",
        ),
        types = model.types?.map { type ->
            findType(type)
        } ?: listOf(),
        fast = model.attacks?.fast?.map { fast ->
            fast?.let {
                Attack(
                    name = fast.name ?: "",
                    type = findType(fast.type),
                    damage = fast.damage ?: 0
                )
            } ?: Attack()
        } ?: listOf(),
        special = model.attacks?.special?.map { special ->
            special?.let {
                Attack(
                    name = special.name ?: "",
                    type = findType(special.type),
                    damage = special.damage ?: 0
                )
            } ?: Attack()
        } ?: listOf(),
        evolutions = model.evolutions?.map { evolution ->
            evolution?.let {
                Pokemon(
                    id = evolution.id,
                    number = evolution.number ?: "",
                    name = evolution.name ?: "",
                    image = evolution.image ?: ""
                )
            } ?: Pokemon()
        } ?: listOf()
    )

    private fun findType(typeName: String?) =
        TypeEnum.values().find { it.label == typeName } ?: TypeEnum.DEFAULT
}