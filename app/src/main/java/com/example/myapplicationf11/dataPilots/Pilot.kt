package com.example.myapplicationf11.dataPilots

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.myapplicationf11.R

data class Pilots (
    @DrawableRes val imageResourceId: Int,
    @StringRes val name: Int,
    val age: Int,
    @StringRes val hobbies: Int
)

val Pilot = listOf(
    Pilots(R.drawable.f1logo, R.string.pilot_name_1, 2, R.string.pilot_description_1),
    Pilots(R.drawable.f1logo, R.string.pilot_name_2, 16, R.string.pilot_description_2),
    Pilots(R.drawable.f1logo, R.string.pilot_name_3, 2, R.string.pilot_description_3),
    Pilots(R.drawable.f1logo, R.string.pilot_name_4, 8, R.string.pilot_description_4),
    Pilots(R.drawable.f1logo, R.string.pilot_name_5, 8, R.string.pilot_description_5),
    Pilots(R.drawable.f1logo, R.string.pilot_name_6, 14, R.string.pilot_description_6),
    Pilots(R.drawable.f1logo, R.string.pilot_name_7, 2, R.string.pilot_description_7),
    Pilots(R.drawable.f1logo, R.string.pilot_name_8, 7, R.string.pilot_description_8),
    Pilots(R.drawable.f1logo, R.string.pilot_name_9, 4, R.string.pilot_description_9)
)
