package ru.aradxxx.ciceronetabs;

import ru.aradxxx.ciceronetabs.command.SwitchTab;
import ru.aradxxx.ciceronetabs.command.TBack;
import ru.aradxxx.ciceronetabs.command.TBackTo;
import ru.aradxxx.ciceronetabs.command.TForward;
import ru.aradxxx.ciceronetabs.command.TReplace;
import ru.aradxxx.ciceronetabs.command.TSwitchTab;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ru.terrakok.cicerone.BaseRouter;
import ru.terrakok.cicerone.Screen;
import ru.terrakok.cicerone.commands.Back;
import ru.terrakok.cicerone.commands.BackTo;
import ru.terrakok.cicerone.commands.Command;
import ru.terrakok.cicerone.commands.Forward;
import ru.terrakok.cicerone.commands.Replace;

/**
 * Класс для высокоуровневого представления навигации
 * Используйте его для выполнения навигации.
 * Данное решение покрывает практически все
 * кейсы необходимые в стандартном приложении.
 * Расширяйте данный класс если необходимо
 * нестандартное поведение.
 */
public class TabRouter extends BaseRouter {
    public TabRouter() {
        super();
    }

    /**
     * Добавляет в цепочку экран.
     * Выполняется на текущем табе
     *
     * @param screen screen
     */
    public void navigateTo(@NonNull Screen screen) {
        executeCommands(new Forward(screen));
    }

    /**
     * Добавляет в цепочку экран.
     * Выполняется на указанном табе
     *
     * @param tabTag tab tag
     * @param screen screen
     */
    public void navigateTo(@NonNull String tabTag, @NonNull Screen screen) {
        executeCommands(new TForward(tabTag, screen));
    }

    /**
     * Очищает всю цепочку и открывает экран как корневой.
     * Выполняется на текущем табе
     *
     * @param screen screen
     */
    public void newRootScreen(@NonNull Screen screen) {
        executeCommands(
                new BackTo(null),
                new Replace(screen)
        );
    }

    /**
     * Очищает всю цепочку и открывает экран как корневой.
     * Выполняется на указанном табе
     *
     * @param tabTag tab tag
     * @param screen screen
     */
    public void newRootScreen(@NonNull String tabTag, @NonNull Screen screen) {
        executeCommands(
                new TBackTo(tabTag, null),
                new TReplace(tabTag, screen)
        );
    }

    /**
     * Заменяет последний экран в цепочке.
     * Выполняется на текущем табе
     *
     * @param screen screen
     */
    public void replaceScreen(@NonNull Screen screen) {
        executeCommands(new Replace(screen));
    }

    /**
     * Заменяет последний экран в цепочке.
     * Выполняется на указанном табе
     *
     * @param tabTag tab tag
     * @param screen screen
     */
    public void replaceScreen(@NonNull String tabTag, @NonNull Screen screen) {
        executeCommands(new TReplace(tabTag, screen));
    }

    /**
     * Возвращает на заданный экран.
     * Выполняется на текущем табе
     *
     * @param screen screen
     */
    public void backTo(@Nullable Screen screen) {
        executeCommands(new BackTo(screen));
    }

    /**
     * Возвращает на заданный экран.
     * Выполняется на указанном табе
     *
     * @param tabTag tab tag
     * @param screen screen
     */
    public void backTo(@NonNull String tabTag, @Nullable Screen screen) {
        executeCommands(new TBackTo(tabTag, screen));
    }

    /**
     * Открывает несколько экранов.
     * Выполняется на текущем табе
     *
     * @param screens screens
     */
    public void newChain(@NonNull Screen... screens) {
        Command[] commands = new Command[screens.length];
        for (int i = 0; i < commands.length; i++) {
            commands[i] = new Forward(screens[i]);
        }
        executeCommands(commands);
    }

    /**
     * Открывает несколько экранов.
     * Выполняется на указанном табе
     *
     * @param tabTag  tab tag
     * @param screens screens
     */
    public void newChain(@NonNull String tabTag, @NonNull Screen... screens) {
        Command[] commands = new Command[screens.length];
        for (int i = 0; i < commands.length; i++) {
            commands[i] = new TForward(tabTag, screens[i]);
        }
        executeCommands(commands);
    }

    /**
     * Очищает всю цепочку и открывает несколько экранов.
     * Выполняется на текущем табе
     *
     * @param screens screens
     */
    public void newRootChain(@NonNull Screen... screens) {
        Command[] commands = new Command[screens.length + 1];
        commands[0] = new BackTo(null);
        if (screens.length > 0) {
            commands[1] = new Replace(screens[0]);
            for (int i = 1; i < screens.length; i++) {
                commands[i + 1] = new Forward(screens[i]);
            }
        }
        executeCommands(commands);
    }

    /**
     * Очищает всю цепочку и открывает несколько экранов.
     * Выполняется на указанном табе
     *
     * @param tabTag  tab tag
     * @param screens screens
     */
    public void newRootChain(@NonNull String tabTag, @NonNull Screen... screens) {
        Command[] commands = new Command[screens.length + 1];
        commands[0] = new BackTo(null);
        if (screens.length > 0) {
            commands[1] = new TReplace(tabTag, screens[0]);
            for (int i = 1; i < screens.length; i++) {
                commands[i + 1] = new TForward(tabTag, screens[i]);
            }
        }
        executeCommands(commands);
    }

    /**
     * Удаляет все экраны из цепочки и выходит.
     * Чаще всего используется для закрытия приложения или закрытия вспомогательных цепочек.
     * Выполняется на текущем табе
     */
    public void finishChain() {
        executeCommands(
                new BackTo(null),
                new Back()
        );
    }

    /**
     * Закрывает текущий экран.
     * Выполняется на текущем табе
     */
    public void exit() {
        executeCommands(new Back());
    }

    /**
     * Закрывает текущий экран.
     * Выполняется на указанном табе
     *
     * @param tabTag tab tag
     */
    public void exit(@NonNull String tabTag) {
        executeCommands(new TBack(tabTag));
    }

    /**
     * Переключает таб
     */
    public void switchTab(@NonNull Screen screen) {
        executeCommands(new TSwitchTab(screen));
    }

    void intSwitchTab(@NonNull Screen screen) {
        executeCommands(new SwitchTab(screen));
    }
}
