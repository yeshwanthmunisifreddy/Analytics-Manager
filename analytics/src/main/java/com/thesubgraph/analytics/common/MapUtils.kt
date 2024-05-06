package com.thesubgraph.analytics.common

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken

fun <T> T.serializeToMap(): Map<String, Any?>? {
    return convert()
}

inline fun <I, reified O> I.convert(): O? {
    val gson = Gson()
    val json = gson.toJson(this)
    return try {
        gson.fromJson(json, object : TypeToken<O>() {}.type)
    } catch (e: JsonSyntaxException) {
        Log.d("GSON"," ${e.message}")
        null
    }
}