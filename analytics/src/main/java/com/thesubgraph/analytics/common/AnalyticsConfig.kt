package com.thesubgraph.analytics.common

interface AnalyticsConfig {
    fun initialize()
    fun isInitialized(): Boolean = false
}

class AnalyConfig {
    companion object {
        val ampInstance: AnalyticsConfig? = null
        val googleInstance: AnalyticsConfig? = null
        fun getInstance(analyticsConfig: AnalyticsConfig): AnalyticsConfig? {
            (analyticsConfig as? AmplitudeConfig)?.let {
                if (ampInstance == null) {
                    return AmplitudeConfig(it.key, it.onInitializedListerner)
                }else{
                    return ampInstance
                }
            }
            (analyticsConfig as? GoogleConfig)?.let {
                googleInstance ?: return AmplitudeConfig(it.key, it.onInitializedListerner)
            }
         return null
        }
    }

}

class AmplitudeConfig(val key: String, val onInitializedListerner: OnInitializedListerner) :
    AnalyticsConfig {
        private var isInitialized = false
    init {
        initialize()
    }

    override fun initialize() {
        //Todo Intialize Analytic Sdk using key
        try {
            onInitializedListerner.onInitialized()
            isInitialized = true
        } catch (e: Exception) {
            onInitializedListerner.onFailedToInitialize(e)
        }
    }

    override fun isInitialized(): Boolean {
        return isInitialized
    }

}
class  GoogleConfig(val key: String, val onInitializedListerner: OnInitializedListerner) :
    AnalyticsConfig {
    private var isInitialized = false
    init {
        initialize()
    }

    override fun initialize() {
        //Todo Intialize Analytic Sdk using key
        try {
            onInitializedListerner.onInitialized()
            isInitialized = true
        } catch (e: Exception) {
            onInitializedListerner.onFailedToInitialize(e)
        }
    }

    override fun isInitialized(): Boolean {
        return isInitialized
    }

}