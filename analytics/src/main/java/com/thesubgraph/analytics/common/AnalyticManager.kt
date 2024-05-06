package com.thesubgraph.analytics.common

import android.util.Log

object AnalyticManager {
    fun logEvent(event: AnalyticEvents) {
        val eventName = event.eventName
        //ToDo add support for which Sdk i have to send
        if (AnalyConfig.ampInstance?.isInitialized() == true) {
            when (AnalyConfig.ampInstance) {
                is AmplitudeConfig -> {
                    Log.d("AnalyticManager", "Event: $eventName, ${event}")
                }
            }
        } else {
            Log.d("AnalyticManager", "Amplitude not initialized")
        }
    }

    fun setUserId(userId:String) {
        Log.d("AnalyticManager", "userId: $userId")
    }

    fun setUserName(userName:String) {
        Log.d("AnalyticManager", "userName: $userName")
    }

    fun setUniqueId(uniqueId:String){
        Log.d("AnalyticManager", "uniqueId: $uniqueId")
    }

}