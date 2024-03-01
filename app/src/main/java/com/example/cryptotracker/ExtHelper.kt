package com.example.cryptotracker

import android.app.Activity
import android.view.Window
import android.view.WindowManager
import com.patrykandpatrick.vico.core.entry.FloatEntry

//convert a list of double to a list of FloatEntry
fun List<Double>.toFloatEntryList(): List<FloatEntry> {
    return this.mapIndexed { index, value -> FloatEntry(index.toFloat(), value.toFloat()) }
}

//set the status bar to a specified color
fun Activity.updateStatusBarColor(color: Int) {
    val window: Window = window
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = color
}
