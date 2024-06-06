package com.thesubgraph.analytics.plugins

import com.thesubgraph.analytics.AnalyticsPlugin
import com.thesubgraph.analytics.common.ConfigOption
import com.thesubgraph.analytics.common.OnInitializedListerner
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AppsFlyerPlugin(val option: ConfigOption, val onInitializedListerner: OnInitializedListerner) :
    AnalyticsPlugin {
    private var isInitialized = false

    init {
        initialize()
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun initialize() {
        GlobalScope.launch(Dispatchers.IO) {
            //Todo Intialize Analytic Sdk using key
            try {
                onInitializedListerner.onInitialized()
                isInitialized = true
            } catch (e: Exception) {
                onInitializedListerner.onFailedToInitialize(e)
            }
        }
    }

    override fun isInitialized(): Boolean {
        return isInitialized
    }

    override fun reset() {

    }

}