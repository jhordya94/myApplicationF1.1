package com.example.myapplicationf11.dataPilots

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.myapplicationf11.R
import androidx.compose.runtime.mutableStateListOf


data class Pilots(
    val imageResourceId: Int,
    val name: String,
    val age: Int,
    val escuderia: String
)

val pilotList = mutableStateListOf(
    Pilots(R.drawable.f1logo, "Max Verstappen", 25, "Es el León Holandés"),
    Pilots(R.drawable.f1logo, "Piloto 1", 30, "Descripción"),
    Pilots(R.drawable.f1logo, "Max", 24, "Arial 1212 Arial 1212 Arial 1212"),
    Pilots(R.drawable.f1logo, "Max", 24, "Arial 1212 Arial 1212 Arial 1212"),
    Pilots(R.drawable.f1logo, "Max", 24, "Arial 1212 Arial 1212 Arial 1212")
)



