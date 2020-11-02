package ru.aradxxx.ciceronetabssample.tabfragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ru.aradxxx.ciceronetabssample.AppFragmentScreen;
import ru.aradxxx.ciceronetabssample.Tab;

/**
 * Пример конкретной реализации фрагмента таба.
 */
public class Tab3 extends TabFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        router().newRootScreen(Tab.Tab3.tag(), new AppFragmentScreen("TabFragment 3"));
    }

    @NonNull
    @Override
    protected Tab tab() {
        return Tab.Tab3;
    }
}
