# Analytics Module

This module provides a simple and flexible way to integrate analytics into your Android application. It supports multiple analytics providers such as Amplitude and Google Analytics.

## Installation

To use this module, you need to include it in your project's `build.gradle` file:

```gradle
dependencies {
    implementation project(':analytics')
}
```

## Initialization

Before you can use the analytics module, you need to initialize it. This is typically done in your application's onCreate method:
Please replace `"your_amplitude_key"`, `"user_id"`, `"user_name"`, and `"unique_id"` with your actual values.

```kotlin
 val amplitudeConfig = AmplitudeConfig("your_amplitude_key", object : OnInitializedListerner {
    override fun onInitialized() {
        // Handle successful initialization
    }

    override fun onFailedToInitialize(e: Exception) {
        // Handle failed initialization
    }
})

AnalyConfig.getInstance(amplitudeConfig)
```
## Logging Events
you can create a data class that extends AnalyticEvents:
@Transient is very important this will omit the parameter from event properties 
I am Using Gson Libray to serializeTomap 

```Kotlin
data class HomeClickEvent(
    @Transient override val eventName: String = "home_click",
    @Transient override val analyticTypes: List<AnalyticType>? = listOf(
        AnalyticType.GOOGLE,
        AnalyticType.AMPLITUDE
    ),
    val page_source: String? = null,
) : AnalyticEvents
```
To log events, you can use the logEvent method of the AnalyticManager
```
val homeClickEvent = HomeClickEvent(page_source = "home")
AnalyticManager.logEvent(homeClickEvent)
```
## Setting User Information
You can set user information using the setUserId, setUserName, and setUniqueId methods of the AnalyticManager:
```Kotlin
AnalyticManager.setUserId("user_id")
AnalyticManager.setUserName("user_name")
AnalyticManager.setUniqueId("unique_id")
