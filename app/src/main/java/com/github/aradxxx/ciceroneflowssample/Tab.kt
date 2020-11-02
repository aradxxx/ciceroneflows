package com.github.aradxxx.ciceroneflowssample

import androidx.annotation.MenuRes
import com.github.aradxxx.ciceroneflowssample.tabfragment.Tab1Screen
import com.github.aradxxx.ciceroneflowssample.tabfragment.Tab2Screen
import com.github.aradxxx.ciceroneflowssample.tabfragment.Tab3Screen

/**
 * Пример класса с тегами табов.
 */
enum class Tab(private val menuItemId: Int, private val tag: String) {
    Tab1(R.id.tab1, Tab1Screen().screenKey),
    Tab2(R.id.tab2, Tab2Screen().screenKey),
    Tab3(R.id.tab3, Tab3Screen().screenKey);

    fun menuItemId(): Int {
        return menuItemId
    }

    fun tag(): String {
        return tag
    }

    companion object {
        fun fromMenuItemId(@MenuRes menuItemId: Int): Tab {
            for (tab in values()) {
                if (menuItemId == tab.menuItemId) {
                    return tab
                }
            }
            throw IllegalStateException("Can't find tab with menuItemId = $menuItemId")
        }
    }
}
