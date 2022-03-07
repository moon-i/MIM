package com.moon.morningismiracle

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat

fun Drawable.setDrawableTint(color: String) {
    val drawable: Drawable = DrawableCompat.wrap(this)

    DrawableCompat.setTint(
        drawable.mutate(),
        Color.parseColor(color)
    )
}

fun Drawable.setDrawableTint(@ColorRes color: Int, context: Context) {
    val drawable: Drawable = DrawableCompat.wrap(this)

    DrawableCompat.setTint(
        drawable.mutate(),
        Color.parseColor(colorResourceToString(color, context))
    )
}

fun colorResourceToString(@ColorRes color: Int, context: Context): String {
    return "#" + Integer.toHexString(ContextCompat.getColor(context,color) and 0x00ffffff)
}
