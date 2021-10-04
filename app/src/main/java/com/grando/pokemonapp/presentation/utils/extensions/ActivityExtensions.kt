package com.grando.pokemonapp.presentation.utils.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair

fun Activity.hideKeyboard() {
    if (currentFocus == null) View(this) else currentFocus?.run { hideKeyboard(this) }
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}