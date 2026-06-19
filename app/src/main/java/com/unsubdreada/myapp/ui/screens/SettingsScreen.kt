package com.unsubdreada.myapp.ui.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import com.unsubdreada.myapp.R
import com.unsubdreada.myapp.ui.components.SettingRow
import com.unsubdreada.myapp.ui.theme.PrimaryDark
import com.unsubdreada.myapp.ui.theme.TextPrimary
import com.unsubdreada.myapp.ui.theme.TextSecondary

@Composable
fun SettingsScreen(innerPadding: PaddingValues) {
    var currentSettingScreen by remember { mutableStateOf("main") }

    val context = LocalContext.current
    val sharedPreferences =
        remember { context.getSharedPreferences("finance_prefs", Context.MODE_PRIVATE) }

    var userName by remember {
        mutableStateOf(sharedPreferences.getString("user_name", "Пользователь") ?: "Пользователь")
    }
    var birthDay by remember {
        mutableStateOf(sharedPreferences.getString("user_birthday", "Не указана") ?: "Не указана")
    }
    var currencyCode by remember {
        mutableStateOf(sharedPreferences.getString("user_currency", "RUB") ?: "RUB")
    }

    when (currentSettingScreen) {
        "main" -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(40.dp))

                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.fluentui_system_icons_person),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(100.dp)
                )

                Spacer(Modifier.height(10.dp))

                Text(
                    text = userName,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    color = TextPrimary
                )
                Text(
                    text = birthDay,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Light,
                    color = TextPrimary.copy(alpha = 0.5f)
                )

                Spacer(Modifier.height(40.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .background(
                            color = PrimaryDark,
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    SettingRow(
                        title = "Аккаунт",
                        subTitle = "Имя, дата рождения, валюта",
                        icon = ImageVector.vectorResource(id = R.drawable.fluentui_system_icons_person_starburst),
                        onClick = { currentSettingScreen = "account" }
                    )

                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        color = TextSecondary.copy(0.1f)
                    )

                    SettingRow(
                        title = "Данные и память",
                        subTitle = "Экспорт, импорт, очистить историю",
                        icon = ImageVector.vectorResource(id = R.drawable.fluentui_system_icons_book_database),
                        onClick = { currentSettingScreen = "data" }
                    )
                }

                Spacer(Modifier.weight(1f))

                Text(
                    text = "BPlanner v0.0.1-alpha",
                    fontSize = 10.sp,
                    fontFamily = FontFamily.Monospace,
                    color = TextSecondary
                )

                Text(
                    text = "Dev. for practice SUSU",
                    fontSize = 10.sp,
                    fontFamily = FontFamily.Monospace,
                    color = TextSecondary,
                    modifier = Modifier.offset(y = (-5).dp)
                )
            }
        }

        "account" -> {
            AccountSettingsScreen(
                innerPadding = innerPadding,
                currentName = userName,
                currentBirthDay = birthDay,
                currentCurrencyCode = currencyCode,
                onNameChange = { newName ->
                    userName = newName
                    sharedPreferences.edit { putString("user_name", newName) }
                },
                onBirthDayChange = { newBirthDay ->
                    birthDay = newBirthDay
                    sharedPreferences.edit { putString("user_birthday", newBirthDay) }
                },
                onCurrencyChange = { newCurrency ->
                    currencyCode = newCurrency
                    sharedPreferences.edit { putString("user_currency", newCurrency) }
                },
                onBackClick = { currentSettingScreen = "main" }
            )
        }
    }
}