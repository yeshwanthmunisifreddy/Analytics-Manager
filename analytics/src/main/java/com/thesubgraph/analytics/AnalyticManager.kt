package com.thesubgraph.analytics

import com.thesubgraph.analytics.common.serializeToMap
import com.thesubgraph.analytics.repository.AnalyticsRepository
import com.thesubgraph.analytics.repository.AnalyticsRepositoryImpl

object AnalyticManager {
    private val analyticsRepository: AnalyticsRepository = AnalyticsRepositoryImpl()
    fun logEvent(event: AnalyticEvent) {
        val eventName = event.eventName
        val analyticType = event.analyticTypes
        val properties = event.serializeToMap()
        analyticsRepository.logEvent(eventName, properties, analyticType)
    }
    fun logout() {
        RegisterAnalyticPlugin.ampInstance?.reset()
        RegisterAnalyticPlugin.appFlyerInstance?.reset()
        RegisterAnalyticPlugin.googleInstance?.reset()
        RegisterAnalyticPlugin.moEngageInstance?.reset()
    }
    fun setUserProperties(userProperties: IUserProperties) {
        analyticsRepository.setUserProperties(userProperties)
    }

    fun appOpenEvent(analyticType: List<AnalyticType>? = AnalyticType.entries.toList()) {
        analyticsRepository.appOpenEvent(analyticType)
    }
}



