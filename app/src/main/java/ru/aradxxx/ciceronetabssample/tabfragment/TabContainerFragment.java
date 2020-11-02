package ru.aradxxx.ciceronetabssample.tabfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import dagger.android.support.DaggerFragment;
import ru.aradxxx.ciceronetabs.NavigationContainer;
import ru.aradxxx.ciceronetabs.TabCicerone;
import ru.aradxxx.ciceronetabs.TabNavigator;
import ru.aradxxx.ciceronetabs.TabRouter;
import ru.aradxxx.ciceronetabssample.MainActivity;
import ru.aradxxx.ciceronetabssample.R;
import ru.aradxxx.ciceronetabssample.Tab;
import ru.aradxxx.ciceronetabssample.TabListener;

/**
 * Пример базового класса фрагмента контейнера табов.
 */
public class TabContainerFragment extends DaggerFragment
        implements NavigationContainer<TabRouter>,
        TabListener,
        BottomNavigationView.OnNavigationItemSelectedListener,
        BottomNavigationView.OnNavigationItemReselectedListener {
    @Inject
    TabCicerone<TabRouter> tabCicerone;
    @Nullable
    private TabNavigator<TabRouter> tabNavigator;
    private BottomNavigationView bottomNavigationView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_container, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bottomNavigationView = view.findViewById(R.id.bottom_navigation_view);
    }

    @Override
    public void onResume() {
        super.onResume();
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setOnNavigationItemReselectedListener(this);
        tabCicerone.tabsContainerCicerone().getNavigatorHolder().setNavigator(tabNavigator());
    }

    @Override
    public void onPause() {
        tabCicerone.tabsContainerCicerone().getNavigatorHolder().removeNavigator();
        super.onPause();
    }

    @NonNull
    @Override
    public TabRouter router() {
        return tabCicerone.tabsContainerRouter();
    }

    // переключаем вкладку при выборе BNV меню
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        bottomNavigationView.setOnNavigationItemSelectedListener(null);
        bottomNavigationView.setOnNavigationItemReselectedListener(null);
        switch (menuItem.getItemId()) {
            case R.id.tab1:
                router().switchTab(new Tab1Screen());
                break;
            case R.id.tab2:
                router().switchTab(new Tab2Screen());
                break;
            case R.id.tab3:
                router().switchTab(new Tab3Screen());
                break;
            default:
                break;
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setOnNavigationItemReselectedListener(this);
        return true;
    }

    // Сбрасываем стек вкладки при повторном нажатии на таб в BNV
    @Override
    public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
        router().backTo(Tab.fromMenuItemId(menuItem.getItemId()).tag(), null);
    }

    @Override
    public void tabResumed(@NonNull Tab tab) {
        bottomNavigationView.setOnNavigationItemSelectedListener(null);
        bottomNavigationView.setOnNavigationItemReselectedListener(null);
        bottomNavigationView.setSelectedItemId(tab.menuItemId());
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setOnNavigationItemReselectedListener(this);
    }

    @NonNull
    private TabNavigator tabNavigator() {
        if (tabNavigator == null) {
            MainActivity activity = (MainActivity) requireActivity();
            tabNavigator = new TabNavigator<>(activity, tabCicerone, getChildFragmentManager(), R.id.ftc_container);
        }
        return tabNavigator;
    }
}
