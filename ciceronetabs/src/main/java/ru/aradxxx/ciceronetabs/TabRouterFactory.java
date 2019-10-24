package ru.aradxxx.ciceronetabs;

import androidx.annotation.NonNull;

/**
 * Фабрика роутеров
 *
 * @param <R> класс роутера
 */
public interface TabRouterFactory<R extends TabRouter> {
    @NonNull
    R create();
}
