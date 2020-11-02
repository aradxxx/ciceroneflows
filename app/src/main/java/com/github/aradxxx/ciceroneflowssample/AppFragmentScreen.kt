package com.github.aradxxx.ciceroneflowssample

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.github.aradxxx.ciceroneflowssample.AppFragment.Companion.newInstance

/**
 * Пример экрана.
 */
class AppFragmentScreen(private val label: String) : FragmentScreen(
    createFragment = {
        newInstance(label)
    }
)
