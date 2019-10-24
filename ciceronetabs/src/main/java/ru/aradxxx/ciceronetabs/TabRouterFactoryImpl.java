package ru.aradxxx.ciceronetabs;

import androidx.annotation.NonNull;

/**
 * Реализация фабрики роутеров, для использования TabRouter
 */
public class TabRouterFactoryImpl implements TabRouterFactory<TabRouter> {
    @NonNull
    @Override
    public TabRouter create() {
        return new TabRouter();
    }
}
