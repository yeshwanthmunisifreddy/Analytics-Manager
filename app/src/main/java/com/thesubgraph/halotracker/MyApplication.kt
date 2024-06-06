package com.thesubgraph.halotracker

import android.app.Application
import android.util.Log
import com.amplitude.android.Configuration
import com.amplitude.core.ServerZone
import com.thesubgraph.analytics.AnalyticType
import com.thesubgraph.analytics.RegisterAnalyticPlugin
import com.thesubgraph.analytics.UserProperties
import com.thesubgraph.analytics.common.ConfigOption
import com.thesubgraph.analytics.common.OnInitializedListerner
import com.thesubgraph.analytics.plugins.amplitude.AmplitudePlugin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val amplitudeConfig =
            AmplitudePlugin(option = ConfigOption.Amplitude(
                amplConfiguration = Configuration(
                    apiKey = "your_api_key",
                    serverZone = ServerZone.US,
                    context = applicationContext,
                ),
            ), object : OnInitializedListerner {
                override fun onInitialized() {
                    Log.d("AnalyticsManager", "Amplitude Initialized")
                }

                override fun onFailedToInitialize(e: Exception) {
                    Log.d("AnalyticsManager", "error + ${e.message}")
                }
            })
        RegisterAnalyticPlugin.getInstance(amplitudeConfig)
        val userProperties = UserProperties()
        userProperties.addProperty(
            "name", "John",
            listOf(AnalyticType.AMPLITUDE, AnalyticType.GOOGLE)
        )

    }
}