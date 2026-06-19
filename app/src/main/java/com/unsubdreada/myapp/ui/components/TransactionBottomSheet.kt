package com.unsubdreada.myapp.ui.components

import TablerCalendar
import TablerCancel
import TablerMoneybagMinus
import TablerTrash
import android.app.DatePickerDialog
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unsubdreada.myapp.data.TransactionEntity
import com.unsubdreada.myapp.model.CurrencyType
import com.unsubdreada.myapp.model.FinanceCategory
import com.unsubdreada.myapp.ui.theme.BlurredPanelBackground
import com.unsubdreada.myapp.ui.theme.ButtonAcceptBackground
import com.unsubdreada.myapp.ui.theme.ButtonAcceptText
import com.unsubdreada.myapp.ui.theme.ButtonCancelBackground
import com.unsubdreada.myapp.ui.theme.ButtonCancelText
import com.unsubdreada.myapp.ui.theme.ButtonDisabledBackground
import com.unsubdreada.myapp.ui.theme.ButtonDisabledText
import com.unsubdreada.myapp.ui.theme.ButtonExpenseBackground
import com.unsubdreada.myapp.ui.theme.ButtonIncomeBackground
import com.unsubdreada.myapp.ui.theme.InputFieldBackground
import com.unsubdreada.myapp.ui.theme.InputTextPlaceholder
import com.unsubdreada.myapp.ui.theme.PrimaryDark
import com.unsubdreada.myapp.ui.theme.TextExpense
import com.unsubdreada.myapp.ui.theme.TextIncome
import com.unsubdreada.myapp.ui.theme.TextSecondary
import com.unsubdreada.myapp.ui.theme.icons.TablerMoneybagPlus
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionBottomSheet(
    transactionEdit: TransactionEntity? = null,
    defaultCurrency: String = "RUB",
    onDismiss: () -> Unit,
    onDeleteClick: (TransactionEntity) -> Unit,
    onConfirm: (Double, Boolean, FinanceCategory, String, String, String) -> Unit
) {
    val context = LocalContext.current
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var amountInput by remember { mutableStateOf(transactionEdit?.amount?.toString() ?: "") }
    val types = listOf(
        "Доход" to TablerMoneybagPlus,
        "Расход" to TablerMoneybagMinus
    )

    var selectedCurrency by remember {
        mutableStateOf(transactionEdit?.currencyCode ?: defaultCurrency)
    }

    var isCurrencyMenuExpanded by remember { mutableStateOf(false) } // управление дропдаун

    val currentCurrencyObject = remember(selectedCurrency) {
        runCatching { CurrencyType.valueOf(selectedCurrency) }.getOrElse { CurrencyType.RUB }
    }

    var selectedType by remember { mutableStateOf(transactionEdit?.isIncome ?: true) }
    var commentInput by remember { mutableStateOf(transactionEdit?.comment ?: "") }
    var selectedCategory by remember(selectedType) {
        mutableStateOf(
            if (transactionEdit != null) {
                runCatching { FinanceCategory.valueOf(transactionEdit.category) }.getOrElse { FinanceCategory.OTHER_EXP }
            } else {
                if (selectedType) FinanceCategory.SALARY else FinanceCategory.PRODUCTS
            }
        )
    }
    val filteredCategories = FinanceCategory.entries.filter { it.isIncome == selectedType }

    val dbFormatter = remember { SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) }
    val uiFormatter = remember { SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()) }
    var selectedDate by remember {
        mutableStateOf(
            transactionEdit?.date ?: dbFormatter.format(Calendar.getInstance().time)
        )
    }
    val calendar = Calendar.getInstance()
    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                selectedDate = dbFormatter.format(calendar.time)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }
    val parsedDate = dbFormatter.parse(selectedDate) ?: Calendar.getInstance().time
    val formatedUiDate = uiFormatter.format(parsedDate)

    val carouselState = rememberLazyListState()

    LaunchedEffect(selectedType) {
        if (selectedCategory.isIncome != selectedType) {
            selectedCategory = if (selectedType) {
                FinanceCategory.SALARY
            } else {
                FinanceCategory.PRODUCTS
            }
        }
    }

    LaunchedEffect(selectedCategory) {
        val index = filteredCategories.indexOf(selectedCategory)
        if (index >= 0) {
            carouselState.animateScrollToItem(index)
        }
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = PrimaryDark,
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            NavigationBar(
                containerColor = BlurredPanelBackground,
                tonalElevation = 0.dp
            ) {
                types.forEachIndexed { index, type ->
                    val isSelected =
                        (index == 0 && selectedType) || (index == 1 && !selectedType)
                    val (title, icon) = type

                    NavigationBarItem(
                        selected = isSelected,
                        onClick = { selectedType = (index == 0) },
                        label = {
                            Text(
                                text = title,
                                fontSize = 15.sp
                            )
                        },
                        icon = {
                            Icon(
                                imageVector = icon,
                                contentDescription = title,
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = if (selectedType) ButtonIncomeBackground else ButtonExpenseBackground,
                            selectedTextColor = if (selectedType) TextIncome else TextExpense,
                            unselectedIconColor = TextSecondary,
                            unselectedTextColor = TextSecondary,
                            indicatorColor = Color.Transparent
                        )

                    )
                }
            }

            Spacer(Modifier.height(10.dp))

            LazyRow(
                state = carouselState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 24.dp)
            ) {
                items(filteredCategories) { category ->
                    FilterChip(
                        selected = selectedCategory == category,
                        onClick = { selectedCategory = category },
                        label = {
                            Text(
                                text = category.title,
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = category.icon,
                                contentDescription = null,
                                tint = category.color
                            )
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            labelColor = category.color,
                            selectedLabelColor = category.color,
                            selectedContainerColor = category.color.copy(alpha = 0.15f)
                        ),
                        border = FilterChipDefaults.filterChipBorder(
                            enabled = true,
                            selected = selectedCategory == category,
                            borderColor = Color.Transparent,
                            selectedBorderWidth = 0.dp,
                            borderWidth = 2.dp
                        )
                    )
                }
            }

            Spacer(Modifier.height(10.dp))

            TextField(
                singleLine = true,
                value = amountInput,
                onValueChange = { amountInput = it },
                shape = RoundedCornerShape(15.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = InputFieldBackground,
                    unfocusedPlaceholderColor = TextSecondary,
                    cursorColor = TextSecondary,
                    focusedTextColor = TextSecondary,
                    unfocusedTextColor = TextSecondary,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                placeholder = {
                    Text(
                        text = "0.00",
                        fontSize = 16.sp,
                        color = InputTextPlaceholder
                    )
                },
                trailingIcon = {
                    Box {
                        IconButton(
                            onClick = { isCurrencyMenuExpanded = true }
                        ) {
                            Icon(
                                imageVector = currentCurrencyObject.symbol,
                                contentDescription = null,
                                tint = TextSecondary,
                                modifier = Modifier.size(22.dp)
                            )
                        }

                        DropdownMenu(
                            expanded = isCurrencyMenuExpanded,
                            onDismissRequest = { isCurrencyMenuExpanded = false }
                        ) {
                            CurrencyType.entries.forEach { currency ->
                                DropdownMenuItem(
                                    text = {
                                        Row() {
                                            Text(currency.title)
                                            Icon(
                                                imageVector = currency.symbol,
                                                contentDescription = null
                                            )
                                        }
                                    },
                                    onClick = {
                                        selectedCurrency = currency.name
                                        isCurrencyMenuExpanded = false
                                    }
                                )
                            }
                        }
                    }
                }
            )

            Spacer(Modifier.height(10.dp))

            AssistChip(
                onClick = { datePickerDialog.show() },
                label = {
                    Text(formatedUiDate)
                },
                leadingIcon = {
                    Icon(TablerCalendar, contentDescription = null)
                },
                modifier = Modifier
                    .height(50.dp)
                    .width(280.dp),
                shape = RoundedCornerShape(15.dp),
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = InputFieldBackground,
                    labelColor = TextSecondary,
                    leadingIconContentColor = TextSecondary
                ),
                border = BorderStroke(width = 0.dp, color = Color.Transparent)
            )

            Spacer(Modifier.height(10.dp))

            TextField(
                singleLine = true,
                value = commentInput,
                onValueChange = { commentInput = it },
                shape = RoundedCornerShape(15.dp),
                placeholder = {
                    Text(
                        text = "Примечание"
                    )
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = InputFieldBackground,
                    unfocusedPlaceholderColor = TextSecondary,
                    focusedContainerColor = InputFieldBackground,
                    cursorColor = TextSecondary,
                    focusedTextColor = TextSecondary,
                    unfocusedTextColor = TextSecondary,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
            )

            Spacer(Modifier.height(10.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Button(
                    onClick = {
                        onDismiss()
                    },
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ButtonCancelBackground,
                        contentColor = ButtonCancelText,
                    )
                ) {
                    Icon(
                        imageVector = TablerCancel,
                        contentDescription = "Отмена"
                    )
                }

                if (transactionEdit != null) {
                    Button(
                        onClick = { onDeleteClick(transactionEdit) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ButtonCancelBackground,
                        )
                    ) {
                        Icon(
                            imageVector = TablerTrash,
                            contentDescription = null,
                            tint = ButtonExpenseBackground
                        )
                    }
                }

                Button(
                    onClick = {
                        val cleanInput = amountInput.trim().replace(",", ".")
                        val amount = cleanInput.toDoubleOrNull() ?: 0.0
                        onConfirm(
                            amount,
                            selectedType,
                            selectedCategory,
                            commentInput,
                            selectedDate,
                            selectedCurrency
                        )
                    },
                    enabled = amountInput.isNotEmpty(),
                    modifier = Modifier.fillMaxWidth(),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ButtonAcceptBackground,
                        contentColor = ButtonAcceptText,
                        disabledContainerColor = ButtonDisabledBackground,
                        disabledContentColor = ButtonDisabledText
                    )
                ) {
                    Text(
                        text = if (transactionEdit != null) "Сохранить" else "Добавить",
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}