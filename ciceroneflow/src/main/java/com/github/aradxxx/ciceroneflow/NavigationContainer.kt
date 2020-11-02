package com.github.aradxxx.ciceroneflow

interface NavigationContainer<out R : FlowRouter> {
    fun router(): R
}
