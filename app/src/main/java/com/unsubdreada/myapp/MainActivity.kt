package com.unsubdreada.myapp

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.unsubdreada.myapp.ui.components.Footer
import com.unsubdreada.myapp.ui.screens.BudgetScreen
import com.unsubdreada.myapp.ui.screens.ChartScreen
import com.unsubdreada.myapp.ui.screens.MainFinanceScreen
import com.unsubdreada.myapp.ui.screens.SettingsScreen
import com.unsubdreada.myapp.ui.screens.StartScreen
import com.unsubdreada.myapp.ui.theme.ScreenBackground
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeSource


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(
            savedInstanceState,
        )
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(android.graphics.Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(android.graphics.Color.TRANSPARENT)
        )
        setContent {
            MaterialTheme {
                val hazeState = remember { HazeState() }
                val context = LocalContext.current
                val sharedPreferences =
                    remember {
                        context.getSharedPreferences(
                            "finance_prefs",
                            Context.MODE_PRIVATE
                        )
                    }

                var userName by remember {
                    mutableStateOf(
                        sharedPreferences.getString("user_name", "") ?: ""
                    )
                }
                var selectedTab by remember { mutableIntStateOf(0) }
                var scrollToTop by remember { mutableStateOf<(() -> Unit)?>(null) }

                if (userName.isBlank()) {
                    StartScreen(onNameSaved = { name -> userName = name })
                } else {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        containerColor = ScreenBackground,
                        bottomBar = {
                            Footer(
                                selectedItem = selectedTab,
                                onTabSelected = { index -> selectedTab = index },
                                onTabReselected = { index ->
                                    if (index == 0) scrollToTop?.invoke()
                                },
                                hazeState = hazeState
                            )
                        }
                    ) { innerPadding ->
                        Box(
                            modifier = Modifier.hazeSource(hazeState)
                        ) {
                            when (selectedTab) {
                                0 -> {
                                    val globalCurrencyCode =
                                        sharedPreferences.getString("user_currency", "RUB") ?: "RUB"
                                    MainFinanceScreen(
                                        innerPadding = innerPadding,
                                        defaultCurrency = globalCurrencyCode,
                                        onScrollToTop = { scrollToTop = it }
                                    )
                                }

                                1 -> ChartScreen(innerPadding = innerPadding)
                                2 -> BudgetScreen(innerPadding = innerPadding)
                                3 -> SettingsScreen(innerPadding = innerPadding)
                            }
                        }
                    }
                }
            }
        }
    }
}
