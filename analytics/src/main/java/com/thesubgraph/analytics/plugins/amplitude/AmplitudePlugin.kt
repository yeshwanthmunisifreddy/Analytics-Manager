package com.thesubgraph.analytics.plugins.amplitude

import com.amplitude.android.Amplitude
import com.thesubgraph.analytics.AnalyticsPlugin
import com.thesubgraph.analytics.common.ConfigOption
import com.thesubgraph.analytics.common.OnInitializedListerner
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AmplitudePlugin(
    val option: ConfigOption.Amplitude,
    val onInitializedListerner: OnInitializedListerner,
) : AnalyticsPlugin {
    private var isInitialized = false
    private var amplitude: Amplitude? = null
    init {
        initialize()
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun initialize() {
        GlobalScope.launch(Dispatchers.IO) {
            amplitude = Amplitude(
                option.amplConfiguration
            )
            isInitialized = true
        }
    }

    override fun isInitialized(): Boolean {
        return isInitialized
    }

    override fun reset() {
        amplitude?.reset()
    }

    fun getAmplitude(): Amplitude? {
        return amplitude
    }

}