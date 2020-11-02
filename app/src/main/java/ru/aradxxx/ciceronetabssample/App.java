package ru.aradxxx.ciceronetabssample;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import ru.aradxxx.ciceronetabssample.di.AppComponent;
import ru.aradxxx.ciceronetabssample.di.DaggerAppComponent;

public final class App extends DaggerApplication {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent component = DaggerAppComponent.builder()
                .bindApplication(this)
                .build();
        component.inject(this);
        return component;
    }
}
