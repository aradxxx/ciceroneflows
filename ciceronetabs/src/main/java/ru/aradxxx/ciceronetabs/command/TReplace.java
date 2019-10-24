package ru.aradxxx.ciceronetabs.command;

import androidx.annotation.NonNull;
import ru.terrakok.cicerone.Screen;
import ru.terrakok.cicerone.commands.Replace;

/**
 * Заменяет текущий экран на указанном табе.
 */
public class TReplace extends Replace {
    @NonNull
    private final String tabTag;

    /**
     * Заменяет текущий экран на указанном табе.
     *
     * @param tabTag Тег таба на котором нужно открыть экран.
     * @param screen Экран который нужно открыть.
     */
    public TReplace(@NonNull String tabTag, @NonNull Screen screen) {
        super(screen);
        this.tabTag = tabTag;
    }

    @NonNull
    public String tabTag() {
        return tabTag;
    }
}
