package com.github.aradxxx.ciceroneflow

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen

open class FlowNavigator<R : FlowRouter>(
    activity: FragmentActivity,
    containerId: Int,
    protected val flowCicerone: FlowCicerone<R>,
    protected val activityFM: FragmentManager = activity.supportFragmentManager,
    fragmentManager: FragmentManager = activity.supportFragmentManager,
    fragmentFactory: FragmentFactory = FragmentFactory()
) : AppNavigator(activity, containerId, fragmentManager, fragmentFactory) {

    override fun applyCommand(command: Command) {
        when (command) {
            is FForward -> fForward(command)
            is FReplace -> fReplace(command)
            is FBackTo -> fBackTo(command)
            is FBack -> fBack(command)
            is FSwitch -> fSwitch(command)
            is Switch -> switch(command)
            else -> super.applyCommand(command)
        }
    }

    protected open fun fForward(command: FForward) {
        flowCicerone.router(command.flowTag).navigateTo(command.screen, command.clearContainer)
    }

    protected open fun fReplace(command: FReplace) {
        flowCicerone.router(command.flowTag).replaceScreen(command.screen)
    }

    protected open fun fBackTo(command: FBackTo) {
        flowCicerone.router(command.flowTag).backTo(command.screen)
    }

    protected open fun fBack(command: FBack) {
        flowCicerone.router(command.flowTag).exit()
    }

    protected open fun fSwitch(command: FSwitch) {
        flowCicerone.flowContainerRouter().intSwitch(command.screen)
    }

    protected open fun switch(command: Switch) {
        val flowTag: String = command.screen.screenKey
        val screen = command.screen
        val newFragment = fragmentManager.findFragmentByTag(flowTag)
        val transaction = fragmentManager.beginTransaction()

        if (null == newFragment) {
            if (screen !is FragmentScreen) {
                throw RuntimeException("Can't create a screen: $flowTag")
            }
            val fragment: Fragment = screen.createFragment(fragmentFactory)
            transaction.add(containerId, fragment, flowTag)
        }
        /*
        Скрываем остальные фрагменты контейнеры
         */
        for (tag in flowCicerone.flowsTags()) {
            if (tag.isNotEmpty() && tag != flowTag) {
                detachIfExists(transaction, tag)
            }
        }

        if (null != newFragment) {
            transaction.attach(newFragment)
        }
        transaction.commit()
    }

    protected open fun detachIfExists(transaction: FragmentTransaction, vararg tags: String) {
        for (tag in tags) {
            val fragment = fragmentManager.findFragmentByTag(tag) ?: continue
            transaction.detach(fragment)
        }
    }

    override fun back() {
        when {
            localStackCopy.isNotEmpty() -> {
                fragmentManager.popBackStack()
                localStackCopy.removeAt(localStackCopy.lastIndex)
            }
            !isActivity() && activityFM.backStackEntryCount != 0 -> {
                flowCicerone.mainRouter().exit()
            }
            else -> {
                super.back()
            }
        }
    }

    protected open fun isActivity() = fragmentManager == activityFM
}