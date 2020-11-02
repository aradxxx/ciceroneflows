package com.github.aradxxx.ciceroneflow

interface FlowRouterFactory<R : FlowRouter> {
    fun create(): R
}
