package com.github.aradxxx.ciceroneflowssample.tabfragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.aradxxx.ciceroneflow.FlowCicerone
import com.github.aradxxx.ciceroneflow.FlowNavigator
import com.github.aradxxx.ciceroneflow.FlowRouter
import com.github.aradxxx.ciceroneflow.NavigationContainer
import dagger.android.support.DaggerFragment
import com.github.aradxxx.ciceroneflowssample.R
import com.github.aradxxx.ciceroneflowssample.Tab
import com.github.aradxxx.ciceroneflowssample.TabListener
import javax.inject.Inject

/**
 * Пример базового класса фрагмента контейнера.
 */
abstract class TabFragment : DaggerFragment(), NavigationContainer<FlowRouter> {
    @Inject
    lateinit var flowCicerone: FlowCicerone<FlowRouter>
    private val flowNavigator by lazy {
        FlowNavigator(
            requireActivity(),
            R.id.fc_container,
            flowCicerone,
            requireActivity().supportFragmentManager,
            childFragmentManager)
    }
    private var tabListener: TabListener? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        val parent = parentFragment
        if (parent is TabListener) {
            tabListener = parent
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tab, container, false)
    }

    override fun onResume() {
        super.onResume()
        flowCicerone.cicerone(tab().tag()).getNavigatorHolder().setNavigator(flowNavigator)
        updateContainerTab()
    }

    override fun onPause() {
        flowCicerone.cicerone(tab().tag()).getNavigatorHolder().removeNavigator()
        super.onPause()
    }

    override fun router(): FlowRouter {
        return flowCicerone.router(tab().tag())
    }

    protected abstract fun tab(): Tab
    private fun updateContainerTab() {
        tabListener?.tabResumed(tab())
    }
}
