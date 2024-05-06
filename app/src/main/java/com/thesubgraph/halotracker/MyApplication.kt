package com.thesubgraph.halotracker

import android.app.Application
import android.util.Log
import com.thesubgraph.analytics.common.AmplitudeConfig
import com.thesubgraph.analytics.common.AnalyConfig
import com.thesubgraph.analytics.common.AnalyticManager
import com.thesubgraph.analytics.common.OnInitializedListerner

class MyApplication: Application(){
    override fun onCreate() {
        super.onCreate()

      val amplitudeConfig = AmplitudeConfig(key = "api_key", object:OnInitializedListerner{
            override fun onInitialized() {
              Log.d("AnalyticsManager", "Amplitude Initialized")
            }

            override fun onFailedToInitialize(e: Exception) {
                Log.d("AnalyticsManager", "error + ${e.message}")
            }
        })
        AnalyConfig.getInstance(amplitudeConfig)

    }
}