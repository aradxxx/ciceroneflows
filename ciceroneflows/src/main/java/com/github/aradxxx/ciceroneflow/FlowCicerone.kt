package com.github.aradxxx.ciceroneflow

import com.github.terrakok.cicerone.Cicerone

private const val FLOWS_CONTAINER = "FLOWS_CONTAINER"
private const val MAIN = "MAIN"

class FlowCicerone<R : FlowRouter>(
    private val routerFactory: FlowRouterFactory<R>
) {
    private val cicerones = mutableMapOf<String, Cicerone<R>>()

    init {
        cicerones[MAIN] = Cicerone.create(routerFactory.create())
    }

    fun mainCicerone(): Cicerone<R> = cicerones[MAIN] ?: throw IllegalStateException("Can't find main cicerone")

    fun flowContainerCicerone(): Cicerone<R> {
        return cicerones.getOrPut(FLOWS_CONTAINER) {
            Cicerone.create(routerFactory.create())
        }
    }

    fun cicerone(flowTag: String): Cicerone<R> {
        return cicerones.getOrPut(flowTag) {
            Cicerone.create(routerFactory.create())
        }
    }

    fun mainRouter() = mainCicerone().router
    fun flowContainerRouter() = flowContainerCicerone().router
    fun router(flowTag: String): R {
        return if (flowTag.isEmpty()) {
            mainCicerone().router
        } else {
            cicerone(flowTag).router
        }
    }

    fun flowsTags(): Iterable<String> = cicerones.keys.filter { it != MAIN && it != FLOWS_CONTAINER }
}