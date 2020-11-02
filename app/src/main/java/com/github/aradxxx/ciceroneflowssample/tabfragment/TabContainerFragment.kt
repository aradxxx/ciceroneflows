package com.github.aradxxx.ciceroneflowssample.tabfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.github.aradxxx.ciceroneflow.FlowCicerone
import com.github.aradxxx.ciceroneflow.FlowNavigator
import com.github.aradxxx.ciceroneflow.FlowRouter
import com.github.aradxxx.ciceroneflow.NavigationContainer
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemReselectedListener
import dagger.android.support.DaggerFragment
import com.github.aradxxx.ciceroneflowssample.R
import com.github.aradxxx.ciceroneflowssample.Tab
import com.github.aradxxx.ciceroneflowssample.TabListener
import javax.inject.Inject

/**
 * Пример базового класса фрагмента контейнера табов.
 */
class TabContainerFragment :
    DaggerFragment(), NavigationContainer<FlowRouter>, TabListener,
    BottomNavigationView.OnNavigationItemSelectedListener, OnNavigationItemReselectedListener {
    @Inject
    lateinit var flowCicerone: FlowCicerone<FlowRouter>
    private val flowNavigator by lazy {
        FlowNavigator(
            requireActivity(),
            R.id.ftc_container,
            flowCicerone,
            requireActivity().supportFragmentManager,
            childFragmentManager)
    }
    private var bottomNavigationView: BottomNavigationView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tab_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomNavigationView = view.findViewById(R.id.bottom_navigation_view)
    }

    override fun onResume() {
        super.onResume()
        bottomNavigationView?.setOnNavigationItemSelectedListener(this)
        bottomNavigationView?.setOnNavigationItemReselectedListener(this)
        flowCicerone.flowContainerCicerone().getNavigatorHolder().setNavigator(flowNavigator)
    }

    override fun onPause() {
        flowCicerone.flowContainerCicerone().getNavigatorHolder().removeNavigator()
        super.onPause()
    }

    override fun onDestroyView() {
        bottomNavigationView = null
        super.onDestroyView()
    }

    override fun router(): FlowRouter {
        return flowCicerone.flowContainerRouter()
    }

    // переключаем вкладку при выборе BNV меню
    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        bottomNavigationView?.setOnNavigationItemSelectedListener(null)
        bottomNavigationView?.setOnNavigationItemReselectedListener(null)
        when (menuItem.itemId) {
            R.id.tab1 -> router().switch(Tab1Screen())
            R.id.tab2 -> router().switch(Tab2Screen())
            R.id.tab3 -> router().switch(Tab3Screen())
        }
        bottomNavigationView?.setOnNavigationItemSelectedListener(this)
        bottomNavigationView?.setOnNavigationItemReselectedListener(this)
        return true
    }

    // Сбрасываем стек вкладки при повторном нажатии на таб в BNV
    override fun onNavigationItemReselected(menuItem: MenuItem) {
        router().backTo(Tab.fromMenuItemId(menuItem.itemId).tag(), null)
    }

    override fun tabResumed(tab: Tab) {
        bottomNavigationView?.setOnNavigationItemSelectedListener(null)
        bottomNavigationView?.setOnNavigationItemReselectedListener(null)
        bottomNavigationView?.selectedItemId = tab.menuItemId()
        bottomNavigationView?.setOnNavigationItemSelectedListener(this)
        bottomNavigationView?.setOnNavigationItemReselectedListener(this)
    }
}
