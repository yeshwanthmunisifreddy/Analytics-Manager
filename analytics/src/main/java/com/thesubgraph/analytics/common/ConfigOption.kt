package com.thesubgraph.analytics.common

import android.app.Application
import com.amplitude.android.Configuration

sealed class ConfigOption {
    data class Google(val key: String, val application: Application) : ConfigOption()
    data class MoEngage(
        val moEngage: com.moengage.core.MoEngage,
        val application: Application,
    ) : ConfigOption()

    data class Amplitude(
        val amplConfiguration: Configuration,
    ) : ConfigOption()

    data class AppsFlyer(
        val key: String,
        val application: Application,
    ) : ConfigOption()
}