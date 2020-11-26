package com.github.aradxxx.ciceroneflowssample.tabfragment

import com.github.aradxxx.ciceroneflowssample.Tab
import com.github.terrakok.cicerone.androidx.FragmentScreen

/**
 * Пример конкретной реализации фрагмента таба.
 */
class Tab1 : TabFragment() {
    override fun tab(): Tab {
        return Tab.Tab1
    }
}

class Tab2 : TabFragment() {
    override fun tab(): Tab {
        return Tab.Tab2
    }
}

class Tab3 : TabFragment() {
    override fun tab(): Tab {
        return Tab.Tab3
    }
}

/**
 * Пример реализации экрана таба.
 */
fun tab1Screen() = FragmentScreen("Tab1") {
    Tab1()
}

fun tab2Screen() = FragmentScreen("Tab2") {
    Tab2()
}

fun tab3Screen() = FragmentScreen("Tab3") {
    Tab3()
}
