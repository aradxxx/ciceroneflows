package ru.aradxxx.ciceronetabssample;

import android.os.Bundle;

import ru.aradxxx.ciceronetabs.NavigationContainer;
import ru.aradxxx.ciceronetabs.TabCicerone;
import ru.aradxxx.ciceronetabs.TabNavigator;
import ru.aradxxx.ciceronetabs.TabRouter;
import ru.aradxxx.ciceronetabssample.R;

import ru.aradxxx.ciceronetabssample.tabfragment.Tab1Screen;
import ru.aradxxx.ciceronetabssample.tabfragment.Tab2Screen;
import ru.aradxxx.ciceronetabssample.tabfragment.Tab3Screen;
import ru.aradxxx.ciceronetabssample.tabfragment.TabContainerScreen;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import dagger.android.support.DaggerAppCompatActivity;

/**
 * Пример активити приложения
 */
public class MainActivity extends DaggerAppCompatActivity
        implements NavigationContainer<TabRouter> {
    @Inject
    TabCicerone<TabRouter> tabCicerone;
    private TabNavigator<TabRouter> navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigator = new TabNavigator<>(this, tabCicerone, R.id.container);

        if (savedInstanceState == null) {
            firstNavigation();
        }
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        tabCicerone.activityCicerone().getNavigatorHolder().setNavigator(navigator);
    }

    @Override
    protected void onPause() {
        tabCicerone.activityCicerone().getNavigatorHolder().removeNavigator();
        super.onPause();
    }

    @NonNull
    @Override
    public TabRouter router() {
        return tabCicerone.activityRouter();
    }

    private void firstNavigation() {
        router().newRootScreen(new TabContainerScreen());
        router().switchTab(new Tab2Screen());
        router().switchTab(new Tab3Screen());
        router().switchTab(new Tab1Screen());
        router().newRootScreen(Tab.Tab1.tag(), new AppFragmentScreen("FROM ACTIVITY"));
    }
}
