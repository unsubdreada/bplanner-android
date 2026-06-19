package com.unsubdreada.myapp.ui.screens

import TablerChevronLeft
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unsubdreada.myapp.R
import com.unsubdreada.myapp.model.CurrencyType
import com.unsubdreada.myapp.ui.components.SettingRow
import com.unsubdreada.myapp.ui.theme.PrimaryDark
import com.unsubdreada.myapp.ui.theme.ScreenBackground
import com.unsubdreada.myapp.ui.theme.TextAdditional
import com.unsubdreada.myapp.ui.theme.TextPrimary
import com.unsubdreada.myapp.ui.theme.TextSecondary
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun AccountSettingsScreen(
    innerPadding: PaddingValues,
    currentName: String,
    currentBirthDay: String,
    currentCurrencyCode: String,
    onNameChange: (String) -> Unit,
    onBirthDayChange: (String) -> Unit,
    onCurrencyChange: (String) -> Unit,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current

    var nameInput by remember {
        mutableStateOf(currentName)
    }
    var birthDayText by remember {
        mutableStateOf(currentBirthDay)
    }

    val birthDayFormatter = remember { SimpleDateFormat("dd.MM.yyyy", Locale.US) }

    val calendar = remember(birthDayText) {
        Calendar.getInstance().apply {
            val parsedDate = runCatching { birthDayFormatter.parse(birthDayText) }.getOrNull()
            if (parsedDate != null) {
                time = parsedDate
            } else {
                time = Date()
            }
        }
    }
    val datePicker = remember {
        android.app.DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                val formatedDate =
                    String.format(Locale.US, "%02d.%02d.%04d", dayOfMonth, month + 1, year)
                birthDayText = formatedDate
                onBirthDayChange(formatedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    var isCurrencyMenuExpanded by remember { mutableStateOf(false) }

    val selectedCurrencyObject = remember(currentCurrencyCode) {
        runCatching { CurrencyType.valueOf(currentCurrencyCode) }.getOrElse { CurrencyType.RUB }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(ScreenBackground)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
                .padding(horizontal = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = TablerChevronLeft,
                    contentDescription = null,
                    tint = TextPrimary
                )
            }

            Spacer(Modifier.width(12.dp))

            Text(
                text = "Аккаунт",
                color = TextPrimary,
                fontSize = 18.sp
            )
        }

        Spacer(Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(
                    color = PrimaryDark,
                    shape = RoundedCornerShape(10.dp)
                )
        ) {
            Text(
                text = "Персональные данные",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = TextAdditional,
                modifier = Modifier
                    .padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
            )

            Spacer(Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.fluentui_system_icons_person_edit),
                    contentDescription = null,
                    tint = Color.Unspecified
                )

                BasicTextField(
                    singleLine = true,
                    value = nameInput,
                    onValueChange = { newInput ->
                        nameInput = newInput
                        onNameChange(newInput)
                    },
                    textStyle = TextStyle(
                        color = TextPrimary,
                        fontSize = 16.sp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, bottom = 16.dp, start = 16.dp),
                    cursorBrush = SolidColor(TextAdditional)
                )
            }

            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 16.dp),
                color = TextSecondary.copy(0.1f)
            )

            SettingRow(
                title = "Дата рождения",
                subTitle = birthDayText,
                icon = ImageVector.vectorResource(id = R.drawable.fluentui_system_icons_calendar_edit),
                onClick = { datePicker.show() }
            )
        }

        Spacer(Modifier.height(12.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(
                    color = PrimaryDark,
                    shape = RoundedCornerShape(10.dp)
                )
        ) {
            Box(Modifier.fillMaxWidth()) {
                SettingRow(
                    title = "Основная валюта",
                    subTitle = selectedCurrencyObject.title,
                    icon = ImageVector.vectorResource(id = R.drawable.fluentui_system_icons_globe),
                    onClick = { isCurrencyMenuExpanded = true }
                )

                DropdownMenu(
                    expanded = isCurrencyMenuExpanded,
                    onDismissRequest = { isCurrencyMenuExpanded = false }
                ) {
                    CurrencyType.entries.forEach { currency ->
                        DropdownMenuItem(
                            text = { Text(currency.title) },
                            leadingIcon = {
                                Icon(
                                    imageVector = currency.symbol,
                                    contentDescription = null
                                )
                            },
                            onClick = {
                                onCurrencyChange(currency.name)
                                isCurrencyMenuExpanded = false
                            }
                        )
                    }
                }
            }
        }
    }
}