package com.thesubgraph.analytics.common

import android.util.Log

object AnalyticManager {
    fun logEvent(event: AnalyticEvents) {
        val eventName = event.eventName
        val analyticType = event.analyticTypes
        val properties = event.serializeToMap()
        analyticType?.forEach {
            when (it) {
                AnalyticType.APPSFLYER ->
                    RegisterAnalyticConfig.appFlyerInstance?.let { config ->
                        if (config is AppsFlyerConfig && config.isInitialized()) {
                            appsflyerLogEvent(eventName, properties)
                        }
                    }

                AnalyticType.AMPLITUDE ->{
                    RegisterAnalyticConfig.ampInstance?.let { config ->
                        if (config is AmplitudeConfig && config.isInitialized()) {
                            amplitudeLogEvent(eventName, properties)
                        }
                    }
                }
                AnalyticType.GOOGLE -> {
                    RegisterAnalyticConfig.googleInstance?.let { config ->
                        if (config is GoogleConfig && config.isInitialized()) {
                            googleLogEvent(eventName, properties)
                        }
                    }
                }
                AnalyticType.MOENGAGE -> {
                    RegisterAnalyticConfig.moEngageInstance?.let { config ->
                        if (config is MoEngageFlyerConfig  && config.isInitialized()) {
                            moEngageLogEvent(eventName, properties)
                        }
                    }
                }
            }
        }
    }

    private fun amplitudeLogEvent(eventName: String, properties: Map<String, Any?>?) {
        Log.d("AMPLITUDE", "Event: $eventName, properties: $properties")
    }

    private fun googleLogEvent(eventName: String, properties: Map<String, Any?>?) {
        Log.d("GOOGLE", "Event: $eventName, properties: $properties")
    }
    private fun appsflyerLogEvent(eventName: String, properties: Map<String, Any?>?) {
        Log.d("APPSFLYER", "Event: $eventName, properties: $properties")
    }
    private fun moEngageLogEvent(eventName: String, properties: Map<String, Any?>?) {
        Log.d("MOENGAGE", "Event: $eventName, properties: $properties")
    }

    fun setUserId(userId: String, analyticType: List<AnalyticType>) {
        Log.d("AnalyticManager", "userId: $userId")
    }

    fun setUserName(userName: String, analyticType: List<AnalyticType>) {
        Log.d("AnalyticManager", "userName: $userName")
    }

    fun setUniqueId(uniqueId: String, analyticType: List<AnalyticType>) {
        Log.d("AnalyticManager", "uniqueId: $uniqueId")
    }

}