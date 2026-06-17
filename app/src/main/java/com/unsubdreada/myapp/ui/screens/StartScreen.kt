package com.unsubdreada.myapp.ui.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
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
fun StartScreen(
    onNameSaved: (String) -> Unit
) {
    val context = LocalContext.current
    val sharedPreferences =
        remember {
            context.getSharedPreferences(
                "finance_prefs",
                Context.MODE_PRIVATE
            )
        }

    var textInput by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .background(PrimaryDark)
            .fillMaxSize(),
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
            shape = RoundedCornerShape(15.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = InputFieldBackground,
                focusedContainerColor = InputFieldBackground,
                cursorColor = TextSecondary,
                unfocusedPlaceholderColor = TextSecondary,
                focusedTextColor = TextSecondary,
                unfocusedTextColor = TextSecondary,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
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
                    onNameSaved(textInput)
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
}