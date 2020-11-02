package com.github.aradxxx.ciceroneflow

import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Screen

data class Switch(
    val screen: Screen
) : Command

data class FSwitch(
    val screen: Screen
) : Command

data class FForward(
    val flowTag: String,
    val screen: Screen,
    val clearContainer: Boolean
) : Command

data class FReplace(
    val flowTag: String,
    val screen: Screen,
) : Command

data class FBack(
    val flowTag: String
) : Command

data class FBackTo(
    val flowTag: String,
    val screen: Screen?,
) : Command
