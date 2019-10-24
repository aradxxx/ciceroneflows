package ru.aradxxx.ciceronetabssample.tabfragment;

import ru.aradxxx.ciceronetabssample.Tab;

import androidx.annotation.NonNull;

/**
 * Пример конкретной реализации фрагмента таба.
 */
public class Tab1 extends TabFragment {
    @NonNull
    @Override
    protected Tab tab() {
        return Tab.Tab1;
    }
}
