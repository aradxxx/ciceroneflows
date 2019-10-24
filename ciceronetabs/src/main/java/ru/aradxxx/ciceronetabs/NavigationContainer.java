package ru.aradxxx.ciceronetabs;

import androidx.annotation.NonNull;

/**
 * Интерфейс контейнера навигации
 *
 * @param <R> класс роутера
 */
public interface NavigationContainer<R extends TabRouter> {
    @NonNull
    R router();
}
