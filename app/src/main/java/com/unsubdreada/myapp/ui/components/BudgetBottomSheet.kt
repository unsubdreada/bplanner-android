package com.unsubdreada.myapp.ui.components

import TablerCancel
import TablerDeviceFloppy
import TablerTrash
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unsubdreada.myapp.data.BudgetEntity
import com.unsubdreada.myapp.model.CurrencyType
import com.unsubdreada.myapp.model.FinanceCategory
import com.unsubdreada.myapp.ui.theme.InputFieldBackground
import com.unsubdreada.myapp.ui.theme.InputTextPlaceholder
import com.unsubdreada.myapp.ui.theme.PrimaryDark
import com.unsubdreada.myapp.ui.theme.RedBackground
import com.unsubdreada.myapp.ui.theme.TextAdditional
import com.unsubdreada.myapp.ui.theme.TextPrimary
import com.unsubdreada.myapp.ui.theme.TextSecondary


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BudgetBottomSheet(
    usedCategories: Set<String> = emptySet(),
    existingBudget: BudgetEntity? = null,
    onDismiss: () -> Unit,
    onSave: (
        category: FinanceCategory,
        amount: Double,
        currency: String
    ) -> Unit,
    onDelete: (() -> Unit)? = null
) {
    val expCategories = FinanceCategory.entries.filter { !it.isIncome }

    var selectedCategory by remember {
        mutableStateOf(existingBudget?.let {
            runCatching { FinanceCategory.valueOf(it.category) }.getOrNull()
        })
    }
    var selectedCurrency by remember {
        mutableStateOf(existingBudget?.let {
            runCatching { CurrencyType.valueOf(it.currencyCode) }.getOrElse { CurrencyType.RUB }
        } ?: CurrencyType.RUB)
    }
    var amountInput by remember {
        mutableStateOf(
            existingBudget?.limitAmount?.toString() ?: ""
        )
    }

    var isCurrencyMenuExpanded by remember { mutableStateOf(false) }

    val carouselState = rememberLazyListState()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = PrimaryDark
    ) {
        LazyRow(
            state = carouselState,
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 24.dp)
        ) {
            items(expCategories) { category ->
                FilterChip(
                    selected = selectedCategory == category,
                    onClick = { selectedCategory = category },
                    enabled = category.name !in usedCategories,
                    label = {
                        Text(
                            text = category.title,
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = category.icon,
                            contentDescription = null,
                            tint = if (category.name in usedCategories) TextSecondary.copy(0.5f) else category.color
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        labelColor = category.color,
                        selectedLabelColor = category.color,
                        selectedContainerColor = category.color.copy(alpha = 0.15f),
                        disabledLabelColor = TextSecondary.copy(0.5f),
                        disabledLeadingIconColor = TextSecondary.copy(0.5f)
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(10.dp),
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
                            imageVector = selectedCurrency.symbol,
                            contentDescription = null,
                            tint = TextSecondary,
                            modifier = Modifier.size(22.dp)
                        )
                    }

                    DropdownMenu(
                        expanded = isCurrencyMenuExpanded,
                        onDismissRequest = { isCurrencyMenuExpanded = false },
                        containerColor = PrimaryDark.copy(0.9f)
                    ) {
                        CurrencyType.entries.forEach { currency ->
                            DropdownMenuItem(
                                text = {
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = currency.title,
                                            color = TextPrimary
                                        )
                                        Icon(
                                            imageVector = currency.symbol,
                                            contentDescription = null,
                                            tint = TextPrimary
                                        )
                                    }
                                },
                                onClick = {
                                    selectedCurrency = currency
                                    isCurrencyMenuExpanded = false
                                }
                            )
                        }
                    }
                }
            }
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            text = "Лимит рассчитывается только по записям в выбранной валюте",
            fontSize = 12.sp,
            color = TextSecondary,
            textAlign = TextAlign.Center
        )

        HorizontalDivider(
            Modifier.padding(top = 10.dp),
            thickness = 2.dp,
            color = TextSecondary.copy(0.5f)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                onClick = onDismiss,
            ) {
                Icon(
                    imageVector = TablerCancel,
                    contentDescription = null,
                    tint = TextSecondary
                )
            }

            if (onDelete != null) {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    ),
                    onClick = { onDelete(); onDismiss() }
                ) {
                    Icon(
                        imageVector = TablerTrash,
                        contentDescription = null,
                        tint = RedBackground
                    )
                }
            }

            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                onClick = {
                    val amount = amountInput.toDoubleOrNull() ?: return@Button
                    val category = selectedCategory ?: return@Button
                    onSave(category, amount, selectedCurrency.name)
                    onDismiss()
                },
                enabled = amountInput.isNotEmpty()
            ) {
                Icon(
                    imageVector = TablerDeviceFloppy,
                    contentDescription = null,
                    tint = TextAdditional
                )
            }


        }
    }
}