package com.unsubdreada.myapp.ui.screens

import TablerAdjustments
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patrykandpatrick.vico.compose.common.Fill
import com.patrykandpatrick.vico.compose.pie.PieChart
import com.patrykandpatrick.vico.compose.pie.PieChartHost
import com.patrykandpatrick.vico.compose.pie.data.PieChartModelProducer
import com.patrykandpatrick.vico.compose.pie.data.pieSeries
import com.patrykandpatrick.vico.compose.pie.rememberPieChart
import com.unsubdreada.myapp.data.AppDatabase
import com.unsubdreada.myapp.model.ChartPeriod
import com.unsubdreada.myapp.model.FinanceCategory
import com.unsubdreada.myapp.ui.components.PeriodBottomSheet
import com.unsubdreada.myapp.ui.theme.PrimaryDark
import com.unsubdreada.myapp.ui.theme.TextPrimary
import com.unsubdreada.myapp.ui.theme.TextSecondary
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChartScreen(innerPadding: PaddingValues) {
    val context = LocalContext.current
    val dao = remember { AppDatabase.getDatabase(context).transactionDao() }
    val allTransactions by dao.getAllTransaction().collectAsState(initial = emptyList())

    var selectedPeriod by remember { mutableStateOf(ChartPeriod.MONTH) }
    var showPeriodSheet by remember { mutableStateOf(false) }
    var showDateRangePicker by remember { mutableStateOf(false) }
    var customStartDate by remember { mutableStateOf<String?>(null) }
    var customEndDate by remember { mutableStateOf<String?>(null) }

    val dateRangePickerState = rememberDateRangePickerState()
    val dbFormatter = remember { SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) }

    // Фильтрация по периоду
    val filteredTransactions =
        remember(allTransactions, selectedPeriod, customStartDate, customEndDate) {
            val startDate = when (selectedPeriod) {
                ChartPeriod.CUSTOM -> customStartDate
                else -> selectedPeriod.startDate()
            }
            val endDate = if (selectedPeriod == ChartPeriod.CUSTOM) customEndDate else null
            allTransactions.filter { t ->
                (startDate == null || t.date >= startDate) && (endDate == null || t.date <= endDate)
            }
        }

    // Суммы расходов по категориям, отсортированные по убыванию
    val expenseByCategory = remember(filteredTransactions) {
        filteredTransactions
            .filter { !it.isIncome }
            .groupBy { t ->
                runCatching { FinanceCategory.valueOf(t.category) }
                    .getOrElse { FinanceCategory.OTHER_EXP }
            }
            .mapValues { (_, list) -> list.sumOf { it.amount } }
            .filter { (_, total) -> total > 0 }
            .toList()
            .sortedByDescending { (_, total) -> total }
            .toMap()
    }

    // Модель данных для Vico
    val modelProducer = remember { PieChartModelProducer() }

    LaunchedEffect(expenseByCategory) {
        if (expenseByCategory.isNotEmpty()) {
            modelProducer.runTransaction {
                pieSeries { series(expenseByCategory.values.map { it.toFloat() }) }
            }
        }
    }

    // Цвета срезов берём из FinanceCategory — порядок совпадает с expenseByCategory
    val pieChart = rememberPieChart(
        sliceProvider = PieChart.SliceProvider.series(
            expenseByCategory.keys.map { category ->
                PieChart.Slice(
                    fill = Fill(category.color)
                )
            }.ifEmpty { listOf(PieChart.Slice(fill = Fill(Color.Transparent))) }
        )
    )

    val headerLabel = if (selectedPeriod == ChartPeriod.CUSTOM) {
        selectedPeriod.dateRangeLabel(customStartDate, customEndDate)
    } else {
        selectedPeriod.dateRangeLabel()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        // Заголовок
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(PrimaryDark.copy(0.9f))
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = headerLabel,
                color = TextPrimary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            IconButton(onClick = { showPeriodSheet = true }) {
                Icon(
                    imageVector = TablerAdjustments,
                    contentDescription = "Выбор периода",
                    tint = TextSecondary,
                    modifier = Modifier.size(22.dp)
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        // Диаграмма
        if (expenseByCategory.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                PieChartHost(
                    chart = pieChart,
                    modelProducer = modelProducer,
                    modifier = Modifier.size(260.dp),
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Нет расходов за выбранный период",
                    color = TextSecondary,
                    fontSize = 14.sp
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        // Легенда
        val totalExpense = expenseByCategory.values.sum()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp)
                .background(PrimaryDark, shape = RoundedCornerShape(10.dp))
                .padding(horizontal = 16.dp)
        ) {
            if (expenseByCategory.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "История расходов",
                        color = TextPrimary
                    )
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(
                    items = expenseByCategory.entries.toList(),
                    key = { it.key.name }
                ) { (category, amount) ->
                    val percent = if (totalExpense > 0)
                        (amount / totalExpense * 100).roundToInt() else 0

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .background(category.color, CircleShape)
                        )

                        Text(
                            text = category.title,
                            color = TextPrimary,
                            fontSize = 14.sp,
                            modifier = Modifier.weight(1f)
                        )

                        Text(
                            text = "$percent%",
                            color = category.color,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "%.2f".format(amount),
                            color = TextSecondary,
                            fontSize = 13.sp
                        )
                    }
                }
            }
        }
    }

    if (showPeriodSheet) {
        PeriodBottomSheet(
            currentPeriod = selectedPeriod,
            onDismiss = { showPeriodSheet = false },
            onPeriodSelected = { period ->
                selectedPeriod = period
                if (period == ChartPeriod.CUSTOM) {
                    showPeriodSheet = false
                    showDateRangePicker = true
                }
            }
        )
    }

    if (showDateRangePicker) {
        DatePickerDialog(
            onDismissRequest = { showDateRangePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    val startMillis = dateRangePickerState.selectedStartDateMillis
                    val endMillis = dateRangePickerState.selectedEndDateMillis
                    if (startMillis != null && endMillis != null) {
                        customStartDate = dbFormatter.format(Date(startMillis))
                        customEndDate = dbFormatter.format(Date(endMillis))
                    }
                    showDateRangePicker = false
                }) { Text("OK") }
            },
            dismissButton = {
                TextButton(onClick = { showDateRangePicker = false }) { Text("Отмена") }
            }
        ) {
            DateRangePicker(
                state = dateRangePickerState,
                title = { Text(text = "Выберите период", modifier = Modifier.padding(16.dp)) },
                headline = null,
                showModeToggle = true
            )
        }
    }
}
