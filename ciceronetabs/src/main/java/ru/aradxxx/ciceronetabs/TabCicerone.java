package ru.aradxxx.ciceronetabs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import androidx.annotation.NonNull;
import ru.terrakok.cicerone.Cicerone;

/**
 * Класс предоставляющий доступ к компонентам библиотеки,
 * адаптированный для использования с таб экранами.
 */
public class TabCicerone<R extends TabRouter> {
    @NonNull
    private static final String TAB_CONTAINER_ROUTER = "TAB_CONTAINER_ROUTER";
    @NonNull
    private static final String ACTIVITY_ROUTER = "ACTIVITY_ROUTER";
    @NonNull
    private final Map<String, Cicerone<R>> containers = new HashMap<>();
    @NonNull
    private final TabRouterFactory<R> routerFactory;

    public TabCicerone(@NonNull TabRouterFactory<R> routerFactory) {
        this.routerFactory = routerFactory;
        containers.put(ACTIVITY_ROUTER, Cicerone.create(routerFactory.create()));
    }

    /**
     * @return Cicerone предназначенный для активити
     */
    @NonNull
    public Cicerone<R> activityCicerone() {
        Cicerone<R> cicerone = containers.get(ACTIVITY_ROUTER);
        if (cicerone != null) {
            return cicerone;
        }
        throw new IllegalStateException("Can't find activity's cicerone");
    }

    /**
     * @return Cicerone предназначенный для фрагмента
     * контейнера табов
     */
    @NonNull
    public Cicerone<R> tabsContainerCicerone() {
        if (!containers.containsKey(TAB_CONTAINER_ROUTER)) {
            containers.put(TAB_CONTAINER_ROUTER, Cicerone.create(routerFactory.create()));
        }
        Cicerone<R> cicerone = containers.get(TAB_CONTAINER_ROUTER);
        if (cicerone != null) {
            return cicerone;
        }
        throw new IllegalStateException("Can't find tab container cicerone");
    }

    /**
     * @return Cicerone предназначенный для фрагмента таба
     */
    @NonNull
    public Cicerone<R> cicerone(@NonNull String tabTag) {
        if (tabTag.isEmpty()) {
            return activityCicerone();
        }
        if (!containers.containsKey(tabTag)) {
            containers.put(tabTag, Cicerone.create(routerFactory.create()));
        }
        Cicerone<R> cicerone = containers.get(tabTag);
        if (cicerone != null) {
            return cicerone;
        }
        throw new IllegalStateException("Can't find cicerone for " + tabTag + "tab");
    }

    /**
     * @return Router предназдаченный для активити
     */
    @NonNull
    public R activityRouter() {
        return activityCicerone().getRouter();
    }

    /**
     * @return Router предназдаченный для фрагмента
     * контейнера табов
     */
    @NonNull
    public R tabsContainerRouter() {
        return tabsContainerCicerone().getRouter();
    }

    /**
     * @return Router предназдаченный для фрагмента таба
     */
    @NonNull
    public R router(@NonNull String tabTag) {
        if (tabTag.isEmpty()) {
            return activityRouter();
        }
        return cicerone(tabTag).getRouter();
    }

    /**
     * @return Набор тегов табов
     */
    @NonNull
    final Iterable<String> tabTags() {
        Set<String> tags = new HashSet<>(containers.size());
        for (String string : containers.keySet()) {
            if (!ACTIVITY_ROUTER.equals(string)
                    && !TAB_CONTAINER_ROUTER.equals(string)) {
                tags.add(string);
            }
        }
        return tags;
    }
}
