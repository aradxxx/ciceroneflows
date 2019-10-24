package ru.aradxxx.ciceronetabs.command;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ru.terrakok.cicerone.Screen;
import ru.terrakok.cicerone.commands.BackTo;

/**
 * Переход назад по цепочке на указанный экран на указанном табе.
 */
public class TBackTo extends BackTo {
    @NonNull
    private final String tabTag;

    public TBackTo(@NonNull String tabTag, @Nullable Screen screen) {
        super(screen);
        this.tabTag = tabTag;
    }

    @NonNull
    public String tabTag() {
        return tabTag;
    }
}
