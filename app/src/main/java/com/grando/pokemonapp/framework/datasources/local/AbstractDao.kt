package com.grando.pokemonapp.framework.datasources.local

interface AbstractDao<T> {

    fun setData(data: T)

    fun getData(): T?
}