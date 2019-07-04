package com.leonardoalves.githubrepository.view.utils

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.core.content.ContextCompat

object ColorUtils {
    fun parse(color: String) = Color.parseColor(color)

    fun parse(context: Context, color: Int) = ContextCompat.getColor(context, color)

    fun stateListOf(color: String): ColorStateList = ColorStateList.valueOf(parse(color))

    fun stateListOf(color: Int): ColorStateList = ColorStateList.valueOf(color)
}