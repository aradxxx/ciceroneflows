package ru.aradxxx.ciceronetabs.command;

import androidx.annotation.NonNull;
import ru.terrakok.cicerone.Screen;
import ru.terrakok.cicerone.commands.Forward;

/**
 * Открытие нового экрана на указанном табе.
 */
public class TForward extends Forward {
    @NonNull
    private final String tabTag;

    /**
     * Открытие нового экрана на указанном табе.
     *
     * @param tabTag Тег таба на котором нужно открыть экран.
     * @param screen Экран который нужно открыть.
     */
    public TForward(@NonNull String tabTag, @NonNull Screen screen) {
        super(screen);
        this.tabTag = tabTag;
    }

    @NonNull
    public String tabTag() {
        return tabTag;
    }
}
