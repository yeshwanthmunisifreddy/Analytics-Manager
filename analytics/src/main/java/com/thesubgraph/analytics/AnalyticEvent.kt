package com.thesubgraph.analytics

enum class AnalyticType {
    APPSFLYER, AMPLITUDE, GOOGLE, MOENGAGE
}

interface AnalyticEvent {
    val eventName: String
    val analyticTypes: List<AnalyticType>?
}
