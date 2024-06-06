package com.thesubgraph.analytics

import com.thesubgraph.analytics.plugins.amplitude.AmplitudePlugin
import com.thesubgraph.analytics.plugins.AppsFlyerPlugin
import com.thesubgraph.analytics.plugins.GooglePlugin
import com.thesubgraph.analytics.plugins.moengage.MoEngagePlugin

interface AnalyticsPlugin {
    fun initialize()
    fun isInitialized(): Boolean = false
    fun reset()
}

class RegisterAnalyticPlugin {
    companion object {
        var ampInstance: AnalyticsPlugin? = null
        var googleInstance: AnalyticsPlugin? = null
        var appFlyerInstance: AnalyticsPlugin? = null
        var moEngageInstance: AnalyticsPlugin? = null

        fun getInstance(analyticsPlugin: AnalyticsPlugin): AnalyticsPlugin? {
            return when (analyticsPlugin) {
                is AmplitudePlugin -> {
                    ampInstance ?: run {
                        ampInstance = AmplitudePlugin(
                            analyticsPlugin.option,
                            analyticsPlugin.onInitializedListerner
                        )
                        return@run ampInstance
                    }
                }

                is GooglePlugin -> {
                    googleInstance ?: run {
                        googleInstance = GooglePlugin(
                            analyticsPlugin.option,
                            analyticsPlugin.onInitializedListerner
                        )
                        return@run googleInstance
                    }
                }

                is AppsFlyerPlugin -> {
                    appFlyerInstance ?: run {
                        appFlyerInstance =
                            AppsFlyerPlugin(
                                analyticsPlugin.option,
                                analyticsPlugin.onInitializedListerner
                            )
                        return@run appFlyerInstance
                    }
                }

                is MoEngagePlugin -> {
                    moEngageInstance ?: run {
                        moEngageInstance =
                            MoEngagePlugin(
                                analyticsPlugin.option,
                                analyticsPlugin.onInitializedListerner
                            )
                        return@run moEngageInstance
                    }
                }

                else -> null
            }
        }
    }
}


