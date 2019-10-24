package ru.aradxxx.ciceronetabssample.tabfragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.aradxxx.ciceronetabssample.R;
import ru.aradxxx.ciceronetabs.NavigationContainer;
import ru.aradxxx.ciceronetabs.TabCicerone;
import ru.aradxxx.ciceronetabs.TabNavigator;
import ru.aradxxx.ciceronetabs.TabRouter;
import ru.aradxxx.ciceronetabssample.MainActivity;
import ru.aradxxx.ciceronetabssample.Tab;
import ru.aradxxx.ciceronetabssample.TabListener;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import dagger.android.support.DaggerFragment;

/**
 * Пример базового класса фрагмента таба.
 */
public abstract class TabFragment extends DaggerFragment
        implements NavigationContainer<TabRouter> {
    @Inject
    TabCicerone<TabRouter> tabCicerone;
    @Nullable
    private TabNavigator<TabRouter> tabNavigator;
    private TabListener tabListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Fragment parent = getParentFragment();
        if (parent instanceof TabListener) {
            tabListener = (TabListener) parent;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        tabCicerone.cicerone(tab().tag()).getNavigatorHolder().setNavigator(tabNavigator());
        updateContainerTab();
    }

    @Override
    public void onPause() {
        tabCicerone.cicerone(tab().tag()).getNavigatorHolder().removeNavigator();
        super.onPause();
    }

    @NonNull
    @Override
    public TabRouter router() {
        return tabCicerone.router(tab().tag());
    }

    @NonNull
    protected abstract Tab tab();

    private void updateContainerTab() {
        if (tabListener != null) {
            tabListener.tabResumed(tab());
        }
    }

    @NonNull
    private TabNavigator tabNavigator() {
        if (tabNavigator == null) {
            MainActivity activity = (MainActivity) requireActivity();
            tabNavigator = new TabNavigator<>(activity, tabCicerone, getChildFragmentManager(), R.id.fc_container);
        }
        return tabNavigator;
    }
}
