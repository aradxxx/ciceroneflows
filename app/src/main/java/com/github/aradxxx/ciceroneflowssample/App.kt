package com.github.aradxxx.ciceroneflowssample

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import com.github.aradxxx.ciceroneflowssample.di.AppComponent
import com.github.aradxxx.ciceroneflowssample.di.DaggerAppComponent

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val component: AppComponent = DaggerAppComponent.builder()
            .bindApplication(this)
            .build()
        component.inject(this)
        return component
    }
}
