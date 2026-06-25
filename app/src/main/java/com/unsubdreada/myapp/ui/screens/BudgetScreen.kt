package com.unsubdreada.myapp.ui.screens

import TablerChevronDown
import TablerChevronUp
import TablerPlus
import TablerTrash
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unsubdreada.myapp.data.AppDatabase
import com.unsubdreada.myapp.data.BudgetEntity
import com.unsubdreada.myapp.model.CurrencyType
import com.unsubdreada.myapp.model.FinanceCategory
import com.unsubdreada.myapp.ui.components.BudgetBottomSheet
import com.unsubdreada.myapp.ui.theme.ButtonAcceptBackground
import com.unsubdreada.myapp.ui.theme.ButtonAcceptText
import com.unsubdreada.myapp.ui.theme.PrimaryDark
import com.unsubdreada.myapp.ui.theme.ScreenBackground
import com.unsubdreada.myapp.ui.theme.TextPrimary
import com.unsubdreada.myapp.ui.theme.TextSecondary
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun BudgetScreen(innerPadding: PaddingValues) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    val currentMonth = remember { SimpleDateFormat("yyyy-MM", Locale.getDefault()).format(Date()) }

    val budgets by db.budgetDao().getAllBudgets().collectAsState(initial = emptyList())
    val budgetsByMonth = remember(budgets) { budgets.groupBy { it.month } }
    val expandedMonthTab = remember { mutableStateMapOf<String, Boolean>() }
    val usedCategories = remember(budgets) {
        budgets.filter { it.month == currentMonth }
            .map { it.category }
            .toSet()
    }

    val allTransactions by db.transactionDao().getAllTransaction()
        .collectAsState(initial = emptyList())

    var showSheet by remember { mutableStateOf(false) }
    var editingBudget by remember { mutableStateOf<BudgetEntity?>(null) }

    LaunchedEffect(budgetsByMonth.keys) {
        budgetsByMonth.keys.forEach { month ->
            if (!expandedMonthTab.contains(month)) expandedMonthTab[month] = (month == currentMonth)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text("Бюджеты", color = TextPrimary, fontSize = 20.sp)
            }

            HorizontalDivider(
                modifier = Modifier
                    .height(2.dp)
                    .padding(horizontal = 16.dp),
                color = TextSecondary.copy(0.2f)
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                budgetsByMonth.forEach { (month, monthBudget) ->
                    stickyHeader(key = "header_$month") {
                        MonthSectionHeader(
                            month = month,
                            expanded = expandedMonthTab[month] == true,
                            onToggle = {
                                expandedMonthTab[month] = !(expandedMonthTab[month] ?: true)
                            }
                        )
                    }
                    if (expandedMonthTab[month] == true) {
                        items(monthBudget, key = { it.id }) { budget ->
                            val category =
                                runCatching { FinanceCategory.valueOf(budget.category) }.getOrElse { FinanceCategory.OTHER_EXP }
                            val currency =
                                runCatching { CurrencyType.valueOf(budget.currencyCode) }.getOrElse { CurrencyType.RUB }

                            val spent = remember(allTransactions) {
                                allTransactions
                                    .filter {
                                        !it.isIncome && it.category == budget.category && it.currencyCode == budget.currencyCode && it.date.startsWith(
                                            budget.month
                                        )
                                    }
                                    .sumOf { it.amount }
                            }
                            val progress =
                                if (budget.limitAmount > 0) (spent / budget.limitAmount).toFloat()
                                    .coerceAtMost(1f) else 0f
                            val progressColor = when {
                                progress >= 1f -> Color(0xFFFB4B4B)
                                progress >= 0.8f -> Color(0xFFFFA879)
                                progress >= 0.6f -> Color(0xFFFFC163)
                                progress >= 0.4f -> Color(0xFFFEFF5C)
                                else -> Color(0xFFC0FF33)
                            }

                            if (month == currentMonth) {
                                BudgetCard(
                                    category,
                                    spent,
                                    budget.limitAmount,
                                    currency,
                                    progress,
                                    progressColor,
                                    onClick = { editingBudget = budget }
                                )
                            } else {
                                ArchivedBudgetCard(
                                    category,
                                    spent,
                                    budget.limitAmount,
                                    currency,
                                    onDelete = {
                                        scope.launch { db.budgetDao().deleteBudget(budget) }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(innerPadding)
                .padding(end = 16.dp, bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            FloatingActionButton(
                onClick = {
                    showSheet = true
                },
                containerColor = ButtonAcceptBackground,
                contentColor = ButtonAcceptText,
                shape = CircleShape
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(4.dp)
                ) {
                    Icon(TablerPlus, null)
                }
            }
        }
    }

    if (showSheet || editingBudget != null) {
        BudgetBottomSheet(
            usedCategories = usedCategories.minus(editingBudget?.category ?: ""),
            existingBudget = editingBudget,
            onDismiss = {
                showSheet = false
                editingBudget = null
            },
            onSave = { category, amount, currency ->
                scope.launch {
                    val existing =
                        editingBudget ?: db.budgetDao()
                            .getBudgetByCategoryAndMonth(category.name, currentMonth)
                    if (existing != null) {
                        db.budgetDao().updateBudget(
                            existing.copy(
                                limitAmount = amount,
                                currencyCode = currency
                            )
                        )
                    } else {
                        db.budgetDao().insertBudget(
                            BudgetEntity(
                                category = category.name,
                                limitAmount = amount,
                                currencyCode = currency,
                                month = currentMonth
                            )
                        )
                    }
                }
            },
            onDelete = editingBudget?.let { budget ->
                {
                    scope.launch { db.budgetDao().deleteBudget(budget) }
                }
            }
        )
    }
}

@Composable
fun BudgetCard(
    category: FinanceCategory,
    spent: Double,
    limit: Double,
    currency: CurrencyType,
    progress: Float,
    progressColor: Color,
    onClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(PrimaryDark, RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(category.icon, null, tint = category.color, modifier = Modifier.size(20.dp))

            Spacer(Modifier.width(8.dp))

            Text(category.title, color = TextPrimary, fontWeight = FontWeight.Normal)
        }

        Spacer(Modifier.height(8.dp))

        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier.fillMaxWidth(),
            color = progressColor,
            trackColor = Color.DarkGray
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "%.2f из %.2f ${currency.name}".format(spent, limit),
            color = TextSecondary,
            fontSize = 13.sp
        )
    }
}

@Composable
fun ArchivedBudgetCard(
    category: FinanceCategory,
    spent: Double,
    limit: Double,
    currency: CurrencyType,
    onDelete: () -> Unit
) {
    val difference = limit - spent
    val saved = difference >= 0

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(PrimaryDark, RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    category.icon,
                    null,
                    tint = category.color.copy(0.5f),
                    modifier = Modifier.size(20.dp)
                )

                Spacer(Modifier.width(8.dp))

                Text(category.title, color = TextPrimary.copy(0.5f), fontWeight = FontWeight.Normal)
            }

            IconButton(
                onClick = onDelete
            ) {
                Icon(
                    imageVector = TablerTrash,
                    contentDescription = null,
                    tint = TextSecondary,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Потрачено: %.2f из %.2f ${currency.name}".format(spent, limit),
            color = TextSecondary,
            fontSize = 13.sp
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = if (saved) "Сэкономлено: %.2f ${currency.name}".format(difference)
            else "Перерасход: %.2f ${currency.name}".format(difference),
            color = TextSecondary,
            fontSize = 13.sp
        )
    }
}

@Composable
fun MonthSectionHeader(
    month: String,
    expanded: Boolean,
    onToggle: () -> Unit
) {
    val label = remember(month) {
        runCatching {
            val date = SimpleDateFormat("yyyy-MM", Locale.getDefault()).parse(month)!!
            SimpleDateFormat("LLLL yyyy", Locale("ru")).format(date)
                .replaceFirstChar { it.uppercase() }
        }.getOrElse { month }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(ScreenBackground)
            .clickable(onClick = onToggle)
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            color = TextPrimary,
        )

        Icon(
            imageVector = if (expanded) TablerChevronUp else TablerChevronDown,
            contentDescription = null,
            tint = TextPrimary
        )
    }
}