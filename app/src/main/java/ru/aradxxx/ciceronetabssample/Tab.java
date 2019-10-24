package ru.aradxxx.ciceronetabssample;

import ru.aradxxx.ciceronetabssample.R;

import ru.aradxxx.ciceronetabssample.tabfragment.Tab1Screen;
import ru.aradxxx.ciceronetabssample.tabfragment.Tab2Screen;
import ru.aradxxx.ciceronetabssample.tabfragment.Tab3Screen;

import androidx.annotation.MenuRes;
import androidx.annotation.NonNull;

/**
 * Пример класса с тегами табов.
 */
public enum Tab {
    Tab1(R.id.tab1, new Tab1Screen().getScreenKey()),
    Tab2(R.id.tab2, new Tab2Screen().getScreenKey()),
    Tab3(R.id.tab3, new Tab3Screen().getScreenKey());

    private final int menuItemId;
    private final String tag;

    Tab(int menuItemId, @NonNull String tag) {
        this.menuItemId = menuItemId;
        this.tag = tag;
    }

    @NonNull
    public static Tab fromMenuItemId(@MenuRes int menuItemId) {
        for (Tab tab : Tab.values()) {
            if (menuItemId == tab.menuItemId) {
                return tab;
            }
        }
        throw new IllegalStateException("Can't find tab with menuItemId = " + menuItemId);
    }

    public int menuItemId() {
        return menuItemId;
    }

    @NonNull
    public String tag() {
        return tag;
    }
}
