package com.moon.morningismiracle

import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.DrawableCompat

fun Drawable.setDrawableTint(color: String) {
    val drawable: Drawable = DrawableCompat.wrap(this)

    DrawableCompat.setTint(
        drawable.mutate(),
        Color.parseColor(color)
    )
}