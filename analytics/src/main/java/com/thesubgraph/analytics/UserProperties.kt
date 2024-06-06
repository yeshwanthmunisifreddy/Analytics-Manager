package com.thesubgraph.analytics

import android.view.inspector.InspectionCompanion

interface IUserProperties {
    val properties: MutableMap<String, Pair<Any?, List<AnalyticType>>>
    fun addProperty(
        key: String, value: Any?,
        analyticTypes: List<AnalyticType> = AnalyticType.entries.toList(),
    ) {
        properties[key] = Pair(value, analyticTypes)
    }
}

class UserProperties(
    override val properties: MutableMap<String, Pair<Any?, List<AnalyticType>>> = mutableMapOf(),
) : IUserProperties{
    companion object{
        const val USER_ID = "userId"
        const val UNIQUE_ID = "uniqueId"
    }
}

