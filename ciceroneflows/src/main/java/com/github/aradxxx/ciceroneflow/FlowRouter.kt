package com.github.aradxxx.ciceroneflow

import com.github.terrakok.cicerone.BackTo
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen

open class FlowRouter : Router() {
    fun navigateTo(flowTag: String, screen: Screen, clearContainer: Boolean = true) {
        executeCommands(FForward(flowTag, screen, clearContainer))
    }

    fun newRootScreen(flowTag: String, screen: Screen) {
        executeCommands(
            FBackTo(flowTag, null),
            FReplace(flowTag, screen)
        )
    }

    fun replaceScreen(flowTag: String, screen: Screen) {
        executeCommands(FReplace(flowTag, screen))
    }

    fun backTo(flowTag: String, screen: Screen?) {
        executeCommands(FBackTo(flowTag, screen))
    }

    fun newChain(flowTag: String, vararg screens: Screen, showOnlyTopScreenView: Boolean = true) {
        val commands = screens.map { FForward(flowTag, it, showOnlyTopScreenView) }
        executeCommands(*commands.toTypedArray())
    }

    fun newRootChain(flowTag: String, vararg screens: Screen, showOnlyTopScreenView: Boolean = true) {
        val commands = screens.mapIndexed { index, screen ->
            if (index == 0)
                FReplace(flowTag, screen)
            else
                FForward(flowTag, screen, showOnlyTopScreenView)
        }
        executeCommands(BackTo(null), *commands.toTypedArray())
    }

    fun finishChain(flowTag: String) {
        executeCommands(FBackTo(flowTag, null), FBack(flowTag))
    }

    fun exit(flowTag: String) {
        executeCommands(FBack(flowTag))
    }

    fun switch(screen: Screen) {
        executeCommands(FSwitch(screen))
    }

    fun intSwitch(screen: Screen) {
        executeCommands(Switch(screen))
    }
}
