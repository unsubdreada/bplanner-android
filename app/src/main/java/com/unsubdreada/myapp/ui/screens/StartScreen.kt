package com.unsubdreada.myapp.ui.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import com.unsubdreada.myapp.ui.theme.ButtonAcceptBackground
import com.unsubdreada.myapp.ui.theme.ButtonAcceptText
import com.unsubdreada.myapp.ui.theme.InputFieldBackground
import com.unsubdreada.myapp.ui.theme.PrimaryDark
import com.unsubdreada.myapp.ui.theme.TextPrimary
import com.unsubdreada.myapp.ui.theme.TextSecondary

@Composable
fun StartScreen() {
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

    if (userName.isEmpty()) {
        var textInput by remember { mutableStateOf("") }
        Column(
            modifier = Modifier
                .background(PrimaryDark),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Добро пожаловать!",
                fontSize = 25.sp,
                color = TextPrimary
            )
            Spacer(Modifier.padding(10.dp))
            TextField(
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = InputFieldBackground,
                    unfocusedPlaceholderColor = TextSecondary,
                ),
                value = textInput,
                onValueChange = { textInput = it },
                placeholder = {
                    Text(
                        text = "Ваше имя",
                        fontSize = 16.sp
                    )
                }
            )

            Spacer(Modifier.padding(10.dp))

            Button(
                onClick = {
                    if (textInput.isNotBlank()) {
                        sharedPreferences.edit { putString("user_name", textInput) }
                        userName = textInput
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = ButtonAcceptBackground,
                    contentColor = ButtonAcceptText
                )
            ) {
                Text(
                    text = "Запомнить",
                    fontSize = 16.sp
                )
            }
        }
    } else {
        MainFinanceScreen()
    }
}