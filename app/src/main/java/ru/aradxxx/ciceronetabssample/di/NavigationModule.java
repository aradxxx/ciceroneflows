package ru.aradxxx.ciceronetabssample.di;

import ru.aradxxx.ciceronetabs.TabCicerone;
import ru.aradxxx.ciceronetabs.TabRouter;
import ru.aradxxx.ciceronetabs.TabRouterFactoryImpl;

import javax.inject.Singleton;

import androidx.annotation.NonNull;
import dagger.Module;
import dagger.Provides;

@Module
public class NavigationModule {
    @Provides
    @Singleton
    @NonNull
    TabCicerone<TabRouter> provideTabRouterHolder() {
        return new TabCicerone<>(new TabRouterFactoryImpl());
    }
}
