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
    Pilots(R.drawable.max1, "Max Verstappen", 25, "Corre en Red Bull"),
    Pilots(R.drawable.yuki, "Yuki Tsunoda", 30, "Corre en Red Bull"),
    Pilots(R.drawable.leclerc, "Charles Leclerc", 24, "Corre en Ferrari"),
    Pilots(R.drawable.lewis, "Lewis Hamilton", 24, "Corre en Ferrari"),
    Pilots(R.drawable.lando, "Lando Norris", 24, "Corre en McLaren")
)





