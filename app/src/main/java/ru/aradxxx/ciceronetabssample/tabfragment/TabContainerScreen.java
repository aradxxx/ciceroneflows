package ru.aradxxx.ciceronetabssample.tabfragment;

import androidx.fragment.app.Fragment;
import ru.terrakok.cicerone.android.support.SupportAppScreen;

/**
 * Пример реализации экрана контейнера табов.
 */
public class TabContainerScreen extends SupportAppScreen {
    @Override
    public Fragment getFragment() {
        return new TabContainerFragment();
    }
}
