package com.thesubgraph.analytics.repository

import com.moengage.core.analytics.MoEAnalyticsHelper
import com.thesubgraph.analytics.AnalyticType
import com.thesubgraph.analytics.IUserProperties
import com.thesubgraph.analytics.RegisterAnalyticPlugin
import com.thesubgraph.analytics.UserProperties
import com.thesubgraph.analytics.common.mapToMoEngageProperties
import com.thesubgraph.analytics.plugins.amplitude.AmplitudePlugin
import com.thesubgraph.analytics.plugins.AppsFlyerPlugin
import com.thesubgraph.analytics.plugins.GooglePlugin
import com.thesubgraph.analytics.plugins.moengage.MoEngagePlugin

interface AnalyticsRepository {
    fun appOpenEvent(analyticType: List<AnalyticType>? = AnalyticType.entries.toList())
    fun logEvent(
        eventName: String,
        properties: Map<String, Any?>?,
        analyticType: List<AnalyticType>?,
    )

    fun setUserProperties(userProperties: IUserProperties)
}

class AnalyticsRepositoryImpl : AnalyticsRepository {
    private fun sendEvent(
        eventName: String,
        properties: Map<String, Any?>? = null,
        analyticType: List<AnalyticType>?,
    ) {
        analyticType?.forEach {
            when (it) {
                AnalyticType.APPSFLYER ->
                    RegisterAnalyticPlugin.appFlyerInstance?.let { config ->
                        if (config is AppsFlyerPlugin && config.isInitialized()) {
                            //analyticsRepository.logEvent(eventName, properties,config)
                        }
                    }

                AnalyticType.AMPLITUDE -> {
                    RegisterAnalyticPlugin.ampInstance?.let { config ->
                        if (config is AmplitudePlugin && config.isInitialized()) {
                            config.getAmplitude()?.track(
                                eventType = eventName,
                                eventProperties = properties
                            )
                        }
                    }
                }

                AnalyticType.GOOGLE -> {
                    RegisterAnalyticPlugin.googleInstance?.let { config ->
                        if (config is GooglePlugin && config.isInitialized()) {
                            // googleLogEvent(eventName, properties)
                        }
                    }
                }

                AnalyticType.MOENGAGE -> {
                    RegisterAnalyticPlugin.moEngageInstance?.let { config ->
                        if (config is MoEngagePlugin && config.isInitialized()) {
                            MoEAnalyticsHelper.trackEvent(
                                context = config.option.application.applicationContext,
                                eventName = eventName,
                                properties = properties.mapToMoEngageProperties()
                            )

                        }
                    }
                }
            }
        }
    }

    override fun appOpenEvent(analyticType: List<AnalyticType>?) {
        sendEvent("app_open", analyticType = analyticType)
    }

    override fun logEvent(
        eventName: String,
        properties: Map<String, Any?>?,
        analyticType: List<AnalyticType>?,
    ) {
        sendEvent(eventName, properties, analyticType)
    }

    override fun setUserProperties(userProperties: IUserProperties) {
        userProperties.properties.forEach { (key, value) ->
            if (value.first != null) {
                setUserProperty(key, value)
            }
            setUserProperty(key, value)
        }
    }

    private fun setUserProperty(key: String, property: Pair<Any?, List<AnalyticType>>) {
        val (value, analyticTypes) = property
        if (value!= null){
            analyticTypes.forEach { analyticType ->
                when (analyticType) {
                    AnalyticType.APPSFLYER ->
                        RegisterAnalyticPlugin.appFlyerInstance?.let { config ->
                            if (config is AppsFlyerPlugin && config.isInitialized()) {
                                // appsflyerSetUserProperty(key, value)
                            }
                        }

                    AnalyticType.AMPLITUDE -> {
                        RegisterAnalyticPlugin.ampInstance?.let { config ->
                            if (config is AmplitudePlugin && config.isInitialized()) {
                                if (key == UserProperties.USER_ID) {
                                    config.getAmplitude()?.setUserId(value.toString())
                                } else if (key == UserProperties.UNIQUE_ID) {
                                    config.getAmplitude()?.setDeviceId(value.toString())
                                }
                            }
                        }
                    }

                    AnalyticType.GOOGLE -> {
                        RegisterAnalyticPlugin.googleInstance?.let { config ->
                            if (config is GooglePlugin && config.isInitialized()) {
                                // googleSetUserProperty(key, value)
                            }
                        }
                    }

                    AnalyticType.MOENGAGE -> {
                        RegisterAnalyticPlugin.moEngageInstance?.let { config ->
                            if (config is MoEngagePlugin && config.isInitialized()) {
                                MoEAnalyticsHelper.setUserAttribute(
                                    context = config.option.application.applicationContext,
                                    attributeName = key,
                                    attributeValue = value
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}

