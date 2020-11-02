package com.github.aradxxx.ciceroneflowssample.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import com.github.aradxxx.ciceroneflowssample.App
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBindingModule::class,
    NavigationModule::class
])
interface AppComponent : AndroidInjector<DaggerApplication> {
    fun inject(app: App)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun bindApplication(app: Application): Builder
        fun build(): AppComponent
    }
}
