package ru.aradxxx.ciceronetabssample.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;
import ru.aradxxx.ciceronetabssample.App;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivityBindingModule.class,
        NavigationModule.class,
})
public interface AppComponent extends AndroidInjector<DaggerApplication> {
    void inject(App app);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder bindApplication(Application app);

        AppComponent build();
    }
}
