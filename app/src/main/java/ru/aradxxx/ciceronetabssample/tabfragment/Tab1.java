package ru.aradxxx.ciceronetabssample.tabfragment;

import androidx.annotation.NonNull;
import ru.aradxxx.ciceronetabssample.Tab;

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
