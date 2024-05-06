package com.thesubgraph.analytics.common

interface AnalyticEvents {
    val eventName: String
    //ToDo add support for which Sdk i have send use variable
}

data class HomeClickEvent(
    @Transient override val eventName: String = "home_click",
    val page_source: String? = null,
) : AnalyticEvents