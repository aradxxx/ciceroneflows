package com.github.aradxxx.ciceroneflowssample.tabfragment

import com.github.terrakok.cicerone.androidx.FragmentScreen

/**
 * Пример реализации экрана контейнера табов.
 */
class TabContainerScreen : FragmentScreen(
    createFragment = {
        TabContainerFragment()
    }
)
