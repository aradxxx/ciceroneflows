package ru.aradxxx.ciceronetabs.command;

import androidx.annotation.NonNull;
import ru.terrakok.cicerone.commands.Back;

/**
 * Переход назад по цепочке на указанном табе.
 */
public class TBack extends Back {
    @NonNull
    private final String tabTag;

    public TBack(@NonNull String tabTag) {
        this.tabTag = tabTag;
    }

    @NonNull
    public String tabTag() {
        return tabTag;
    }
}
