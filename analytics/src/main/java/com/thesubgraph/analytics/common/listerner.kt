package com.thesubgraph.analytics.common

interface OnInitializedListerner{
    fun onInitialized()
    fun onFailedToInitialize(e:Exception)
}