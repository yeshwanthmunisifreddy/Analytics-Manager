package com.thesubgraph.halotracker.data.events

import com.thesubgraph.analytics.AnalyticEvent
import com.thesubgraph.analytics.AnalyticType

data class HomeClickEvent(
    @Transient override val eventName: String = "home_click",
    @Transient override val analyticTypes: List<AnalyticType>? = listOf(
        AnalyticType.GOOGLE,
        AnalyticType.AMPLITUDE
    ),
    val page_source: String? = null,
) : AnalyticEvent