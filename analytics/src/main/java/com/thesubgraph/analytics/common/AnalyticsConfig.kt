package com.thesubgraph.analytics.common

interface AnalyticsConfig {
    fun initialize()
    fun isInitialized(): Boolean = false
}

class RegisterAnalyticConfig {
    companion object {
        var ampInstance: AnalyticsConfig? = null
        var googleInstance: AnalyticsConfig? = null
        var appFlyerInstance: AnalyticsConfig? = null
        var moEngageInstance: AnalyticsConfig? = null
        fun getInstance(analyticsConfig: AnalyticsConfig): AnalyticsConfig? {
            (analyticsConfig as? AmplitudeConfig)?.let {
                ampInstance ?: run {
                    ampInstance = AmplitudeConfig(it.key, it.onInitializedListerner)
                    return ampInstance
                }
            }
            (analyticsConfig as? GoogleConfig)?.let {
                googleInstance ?: run { 
                     googleInstance = GoogleConfig(it.key, it.onInitializedListerner)
                    return googleInstance
                }
            }

            (analyticsConfig as? AppsFlyerConfig)?.let {
                appFlyerInstance ?: run { 
                    appFlyerInstance = AppsFlyerConfig(it.key, it.onInitializedListerner)
                    return appFlyerInstance
                }
            }

            (analyticsConfig as? MoEngageFlyerConfig)?.let {
                moEngageInstance ?: run { 
                    moEngageInstance = MoEngageFlyerConfig(it.key, it.onInitializedListerner)
                    return moEngageInstance
                } 
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

class GoogleConfig(val key: String, val onInitializedListerner: OnInitializedListerner) :
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

class AppsFlyerConfig(val key: String, val onInitializedListerner: OnInitializedListerner) :
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

class MoEngageFlyerConfig(val key: String, val onInitializedListerner: OnInitializedListerner) :
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

