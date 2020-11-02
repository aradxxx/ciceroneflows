package com.github.aradxxx.ciceroneflowssample.di

import com.github.aradxxx.ciceroneflow.DefaultFlowRouterFactory
import com.github.aradxxx.ciceroneflow.FlowCicerone
import com.github.aradxxx.ciceroneflow.FlowRouter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NavigationModule {
    @Provides
    @Singleton
    fun provideTabRouterHolder(): FlowCicerone<FlowRouter> {
        return FlowCicerone(DefaultFlowRouterFactory())
    }
}
