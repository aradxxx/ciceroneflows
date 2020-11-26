package com.github.aradxxx.ciceroneflowssample

import com.github.terrakok.cicerone.androidx.FragmentScreen

/**
 * Пример экрана.
 */
fun appFragmentScreen(label: String) = FragmentScreen {
    AppFragment.newInstance(label)
}
