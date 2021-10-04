package com.grando.pokemonapp.presentation.views.adapters

import android.view.View
import com.grando.pokemonapp.domain.Pokemon
import kotlinx.android.synthetic.main.item_evolution.view.*

class EvolutionsAdapter(layoutId: Int) :
    GenericAdapter<Pokemon>(layoutId) {

    override fun onBindViewHolder(holder: GenericViewHolder<Pokemon>, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.itemView.vTopLine?.visibility =
            if (position == 0) View.GONE else View.VISIBLE

        holder.itemView.vBottomLine?.visibility =
            if (position == items.lastIndex) View.GONE else View.VISIBLE
    }
}