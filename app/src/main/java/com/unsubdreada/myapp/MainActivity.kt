package com.unsubdreada.myapp

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.unsubdreada.myapp.ui.screens.MainFinanceScreen
import com.unsubdreada.myapp.ui.screens.SettingsScreen
import com.unsubdreada.myapp.ui.screens.StartScreen
import com.unsubdreada.myapp.ui.theme.ScreenBackground


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(
            savedInstanceState,
        )
        setContent {
            MaterialTheme {
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

                if (userName.isBlank()) {
                    StartScreen(onNameSaved = { name -> userName = name })
                } else {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        containerColor = ScreenBackground,
                        bottomBar = {
                            Footer(
                                selectedItem = selectedTab,
                                onTabSelected = { index -> selectedTab = index }
                            )
                        }
                    ) { innerPadding ->
                        when (selectedTab) {
                            0 -> MainFinanceScreen(innerPadding = innerPadding)
                            1 -> {}
                            2 -> SettingsScreen(innerPadding = innerPadding)
                        }
                    }
                }
            }
        }
    }
}
