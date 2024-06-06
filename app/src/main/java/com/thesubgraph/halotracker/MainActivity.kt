package com.thesubgraph.halotracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.moengage.core.internal.USER_ATTRIBUTE_UNIQUE_ID
import com.thesubgraph.analytics.AnalyticManager
import com.thesubgraph.analytics.AnalyticType
import com.thesubgraph.analytics.UserProperties
import com.thesubgraph.halotracker.data.events.HomeClickEvent
import com.thesubgraph.halotracker.ui.theme.HaloTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HaloTrackerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                     AnalyticManager.logEvent(
                        HomeClickEvent(
                            page_source = "Home"
                        )
                    )
                    val userProperties = UserProperties()
                    userProperties.addProperty(
                        UserProperties.USER_ID, "123456",
                        listOf(AnalyticType.AMPLITUDE, AnalyticType.GOOGLE)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HaloTrackerTheme {
        Greeting("Android")
    }
}