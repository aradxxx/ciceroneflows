package com.github.aradxxx.ciceroneflowssample

import android.os.Bundle
import com.github.aradxxx.ciceroneflow.FlowCicerone
import com.github.aradxxx.ciceroneflow.FlowNavigator
import com.github.aradxxx.ciceroneflow.FlowRouter
import com.github.aradxxx.ciceroneflow.NavigationContainer
import com.github.aradxxx.ciceroneflowssample.tabfragment.tab1Screen
import com.github.aradxxx.ciceroneflowssample.tabfragment.tabContainerScreen
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * Пример активити приложения
 */
class MainActivity : DaggerAppCompatActivity(), NavigationContainer<FlowRouter> {
    @Inject
    lateinit var flowCicerone: FlowCicerone<FlowRouter>
    private val navigator: FlowNavigator<FlowRouter> by lazy {
        FlowNavigator(
            this,
            R.id.container,
            flowCicerone)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            firstNavigation()
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        flowCicerone.mainCicerone().getNavigatorHolder().setNavigator(navigator)
    }

    override fun onPause() {
        flowCicerone.mainCicerone().getNavigatorHolder().removeNavigator()
        super.onPause()
    }

    override fun router(): FlowRouter {
        return flowCicerone.mainRouter()
    }

    private fun firstNavigation() {
        router().newRootScreen(tabContainerScreen())
        router().newRootScreen(Tab.Tab1.tag(), appFragmentScreen("FROM ACTIVITY 1"))
        router().newRootScreen(Tab.Tab3.tag(), appFragmentScreen("FROM ACTIVITY 3"))
        router().newRootScreen(Tab.Tab2.tag(), appFragmentScreen("FROM ACTIVITY 2"))
        router().switch(tab1Screen())
    }
}
