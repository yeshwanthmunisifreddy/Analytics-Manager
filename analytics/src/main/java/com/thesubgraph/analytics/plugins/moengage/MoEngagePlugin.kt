package com.thesubgraph.analytics.plugins.moengage

import com.moengage.core.MoECoreHelper
import com.moengage.core.MoEngage
import com.thesubgraph.analytics.AnalyticsPlugin
import com.thesubgraph.analytics.common.ConfigOption
import com.thesubgraph.analytics.common.OnInitializedListerner
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MoEngagePlugin(
    val option: ConfigOption.MoEngage,
    val onInitializedListerner: OnInitializedListerner,
) : AnalyticsPlugin {
    private var isInitialized = false

    init {
        initialize()
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun initialize() {
        GlobalScope.launch(Dispatchers.IO) {
            MoEngage.initialiseDefaultInstance(option.moEngage)
            isInitialized = true
        }
    }

    override fun isInitialized(): Boolean {
        return isInitialized
    }

    override fun reset() {
        MoECoreHelper.logoutUser(option.application.applicationContext)
    }
}
