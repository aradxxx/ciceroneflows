package ru.aradxxx.ciceronetabssample;

import ru.aradxxx.ciceronetabssample.di.AppComponent;
import ru.aradxxx.ciceronetabssample.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

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
