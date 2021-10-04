package com.grando.pokemonapp.presentation.utils.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageFromUrl")
fun ImageView.src(url: String) = Glide
    .with(context)
    .load(url)
    .into(this)