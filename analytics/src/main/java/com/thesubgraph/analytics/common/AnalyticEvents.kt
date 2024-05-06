package com.thesubgraph.analytics.common

enum class AnalyticType {
    APPSFLYER, AMPLITUDE, GOOGLE, MOENGAGE
}

interface AnalyticEvents {
    val eventName: String
    val analyticTypes: List<AnalyticType>?
    //ToDo add support for which Sdk i have send use variable
}

data class HomeClickEvent(
    @Transient override val eventName: String = "home_click",
    @Transient override val analyticTypes: List<AnalyticType>? = listOf(
        AnalyticType.GOOGLE,
        AnalyticType.AMPLITUDE
    ),
    val page_source: String? = null,
) : AnalyticEvents