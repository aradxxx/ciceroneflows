package ru.aradxxx.ciceronetabssample.tabfragment;

import androidx.fragment.app.Fragment;
import ru.terrakok.cicerone.android.support.SupportAppScreen;

/**
 * Пример реализации экрана таба.
 */
public class Tab2Screen extends SupportAppScreen {
    @Override
    public Fragment getFragment() {
        return new Tab2();
    }
}
