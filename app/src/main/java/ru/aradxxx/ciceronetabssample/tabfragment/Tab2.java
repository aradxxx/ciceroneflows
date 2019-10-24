package ru.aradxxx.ciceronetabssample.tabfragment;

import android.os.Bundle;

import ru.aradxxx.ciceronetabssample.AppFragmentScreen;
import ru.aradxxx.ciceronetabssample.Tab;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Пример конкретной реализации фрагмента таба.
 */
public class Tab2 extends TabFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        router().newRootScreen(new AppFragmentScreen("TabFragment 2"));
    }

    @NonNull
    @Override
    protected Tab tab() {
        return Tab.Tab2;
    }
}
