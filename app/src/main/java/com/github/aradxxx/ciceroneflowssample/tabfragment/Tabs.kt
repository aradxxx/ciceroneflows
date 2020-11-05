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
class Tab1Screen : FragmentScreen(
    fragmentCreator = {
        Tab1()
    }
)

class Tab2Screen : FragmentScreen(
    fragmentCreator = {
        Tab2()
    }
)

class Tab3Screen : FragmentScreen(
    fragmentCreator = {
        Tab3()
    }
)
