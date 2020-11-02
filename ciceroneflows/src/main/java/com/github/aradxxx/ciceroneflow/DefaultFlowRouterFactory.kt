package com.github.aradxxx.ciceroneflow

import com.github.aradxxx.ciceroneflow.FlowRouter
import com.github.aradxxx.ciceroneflow.FlowRouterFactory

class DefaultFlowRouterFactory : FlowRouterFactory<FlowRouter> {
    override fun create(): FlowRouter = FlowRouter()
}
