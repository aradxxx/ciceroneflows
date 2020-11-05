package com.github.aradxxx.ciceroneflowssample

import com.github.aradxxx.ciceroneflowssample.AppFragment.Companion.newInstance
import com.github.terrakok.cicerone.androidx.FragmentScreen

/**
 * Пример экрана.
 */
class AppFragmentScreen(private val label: String) : FragmentScreen(
    fragmentCreator = {
        newInstance(label)
    }
)
