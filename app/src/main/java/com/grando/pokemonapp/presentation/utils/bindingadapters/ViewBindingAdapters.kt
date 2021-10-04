package com.grando.pokemonapp.presentation.utils.bindingadapters

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.core.graphics.ColorUtils
import androidx.databinding.BindingAdapter
import com.grando.pokemonapp.domain.TypeEnum

@BindingAdapter("backgroundByType")
fun View.backgroundByType(list: List<TypeEnum>) {
    val colors = list.map { it.color }
    if (colors.size == 1) {
        this.setBackgroundColor(colors[0])
    } else {
        val gradientDrawable = GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM,
            colors.toIntArray()
        )
        this.background = gradientDrawable
    }
}

@BindingAdapter("moveBackgroundByType")
fun View.moveBackgroundByType(type: TypeEnum) {
    val gradientDrawable = GradientDrawable(
        GradientDrawable.Orientation.BR_TL,
        intArrayOf(
            ColorUtils.setAlphaComponent(type.color, 90),
            Color.WHITE, Color.WHITE
        )
    )
    this.background = gradientDrawable
}