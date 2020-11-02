package com.github.aradxxx.ciceroneflowssample.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.github.aradxxx.ciceroneflowssample.MainActivity

@Module
interface ActivityBindingModule {
    @ContributesAndroidInjector(
        modules = [
            AppActivityModule::class,
            FragmentBindingModule::class
        ]
    )
    fun bindAppActivity(): MainActivity

    @Module
    abstract class AppActivityModule {
        @Binds
        abstract fun bindContext(application: Application): Context
    }
}
