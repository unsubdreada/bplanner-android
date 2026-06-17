package com.unsubdreada.myapp.ui.screens

import TablerCheck
import TablerChecks
import TablerClock
import TablerPlusMinus
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unsubdreada.myapp.data.AppDatabase
import com.unsubdreada.myapp.data.TransactionEntity
import com.unsubdreada.myapp.model.FilterType
import com.unsubdreada.myapp.model.FinanceCategory
import com.unsubdreada.myapp.model.SortType
import com.unsubdreada.myapp.ui.components.Footer
import com.unsubdreada.myapp.ui.components.Header
import com.unsubdreada.myapp.ui.components.SortBottomSheet
import com.unsubdreada.myapp.ui.components.TransactionBottomSheet
import com.unsubdreada.myapp.ui.theme.ButtonAcceptBackground
import com.unsubdreada.myapp.ui.theme.ButtonAcceptText
import com.unsubdreada.myapp.ui.theme.PrimaryDark
import com.unsubdreada.myapp.ui.theme.ScreenBackground
import com.unsubdreada.myapp.ui.theme.TextExpense
import com.unsubdreada.myapp.ui.theme.TextIncome
import com.unsubdreada.myapp.ui.theme.TextPrimary
import com.unsubdreada.myapp.ui.theme.TextSecondary
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainFinanceScreen() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val db = remember { AppDatabase.getDatabase(context) }
    val dao = remember { db.transactionDao() }

    val scrollState = rememberLazyListState()
    val isSearchVisible by remember {
        derivedStateOf { scrollState.firstVisibleItemIndex == 0 }
    }

    var searchQuery by remember { mutableStateOf("") }
    var selectedCategoryFilter by remember { mutableStateOf("") }
    var currentSortType by remember { mutableStateOf(SortType.CREATED_DESC) }
    var currentFilterType by remember { mutableStateOf(FilterType.ALL) }

    val transactionList by dao.getSearchTransactions(
        searchQuery,
        selectedCategoryFilter,
        sortType = currentSortType.name,
        filterType = currentFilterType.name
    )
        .collectAsState(initial = emptyList())

    val allTransactions by dao.getAllTransaction()
        .collectAsState(initial = emptyList())

    val existingCategoryTitle = remember(allTransactions) {
        allTransactions.map { transaction ->
            runCatching {
                FinanceCategory.valueOf(transaction.category)
            }.getOrElse { FinanceCategory.OTHER_EXP }
        }
            .distinctBy { it.title }
            .map { it.title }
    }

    var showSheet by remember { mutableStateOf(false) }

    var showSortSheet by remember { mutableStateOf(false) }

    var selectedIds by remember { mutableStateOf(emptySet<Int>()) } // id выделенных записей
    val isSelectedMode = selectedIds.isNotEmpty()

    var editingTransaction by remember { mutableStateOf<TransactionEntity?>(null) } // Редактируемая запись, null - при добавлении записи

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = ScreenBackground,
        topBar = {
            Header(
                isSelectedMode = isSelectedMode,
                onDeleteClick = {
                    scope.launch {
                        dao.delTransactionsById(selectedIds.toList())
                        selectedIds = emptySet()
                    }
                },
                searchText = searchQuery,
                onSearchTextExchange = { searchQuery = it },
                selectedCategory = selectedCategoryFilter,
                onCategorySelect = { selectedCategoryFilter = it },
                availableFilters = existingCategoryTitle,
                onSortClick = {
                    showSortSheet = true
                },
                isSearchVisible = isSearchVisible,
                scope = scope,
                scrollState = scrollState
            )
        },
        bottomBar = {
            Footer()
        },
        floatingActionButton = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                SmallFloatingActionButton(
                    onClick = {
                        currentFilterType = currentFilterType.next()
                    },
                    shape = CircleShape,
                    containerColor = currentFilterType.color
                ) {
                    Icon(
                        imageVector = currentFilterType.icon,
                        contentDescription = null,
                        tint = TextPrimary
                    )
                }
                Spacer(Modifier.width(10.dp))
                FloatingActionButton(
                    onClick = {
                        editingTransaction = null
                        showSheet = true
                    },
                    containerColor = ButtonAcceptBackground,
                    contentColor = ButtonAcceptText,
                    shape = CircleShape
                ) {
                    Icon(
                        imageVector = TablerPlusMinus,
                        contentDescription = "Добавить"
                    )
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            state = scrollState,
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = innerPadding,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(
                items = transactionList,
                key = { transaction -> transaction.id }
            ) { transaction ->
                val dbFormatter =
                    remember { SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) }
                val uiFormatter =
                    remember { SimpleDateFormat("dd.MM.yy", Locale.getDefault()) }
                val formattedDate = remember(transaction.date) {
                    val parsed =
                        dbFormatter.parse(transaction.date) ?: Calendar.getInstance().time
                    uiFormatter.format(parsed)
                }
                val today = remember { dbFormatter.format(Calendar.getInstance().time) }
                val chipIcon = if (transaction.date > today) {
                    TablerClock
                } else {
                    TablerChecks
                }

                val categoryObject =
                    runCatching { FinanceCategory.valueOf(transaction.category) }
                        .getOrElse { FinanceCategory.OTHER_EXP }

                val isCurrentCardSelection = selectedIds.contains(transaction.id)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp)
                        .combinedClickable(
                            onLongClick = {
                                if (!isSelectedMode) selectedIds = selectedIds + transaction.id
                            },
                            onClick = {
                                if (isSelectedMode) {
                                    selectedIds = if (selectedIds.contains(transaction.id)) {
                                        selectedIds - transaction.id
                                    } else {
                                        selectedIds + transaction.id
                                    }
                                } else {
                                    // TODO: Карточка регистрации
                                    editingTransaction = transaction
                                    showSheet = true
                                }
                            }
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Surface(
                        modifier = Modifier.size(50.dp),
                        shape = CircleShape
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    if (!isCurrentCardSelection) {
                                        categoryObject.color
                                    } else {
                                        categoryObject.color.copy(alpha = 0.5f)
                                    }
                                ),
                            contentAlignment = Alignment.Center,
                        ) {
                            Icon(
                                imageVector = if (!isCurrentCardSelection) {
                                    categoryObject.icon
                                } else {
                                    TablerCheck
                                },
                                contentDescription = null,
                                tint = Color.DarkGray
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 10.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = categoryObject.title,
                            fontSize = 18.sp,
                            color = TextPrimary
                        )
                        Spacer(Modifier.height(5.dp))
                        Text(
                            text = transaction.comment,
                            color = TextSecondary,
                            fontSize = 14.sp,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {

                        SuggestionChip(
                            onClick = {},
                            label = {
                                Text(
                                    text = formattedDate,
                                    style = MaterialTheme.typography.labelMedium
                                )
                            },
                            icon = {
                                Icon(
                                    imageVector = chipIcon,
                                    contentDescription = null,
                                    Modifier.size(16.dp)
                                )
                            },
                            modifier = Modifier
                                .height(20.dp),
                            shape = CircleShape,
                            border = BorderStroke(width = 0.dp, color = Color.Transparent),
                            colors = SuggestionChipDefaults.suggestionChipColors(
                                containerColor = PrimaryDark.copy(0.6f),
                                labelColor = TextSecondary,
                                iconContentColor = TextSecondary
                            )
                        )

                        Text(
                            text = "%.2f ₽".format(Locale.US, transaction.amount),
                            fontSize = 18.sp,
                            color = if (transaction.isIncome) TextIncome else TextExpense
                        )
                    }
                }
            }
        }


    }
    if (showSheet) {
        TransactionBottomSheet(
            transactionEdit = editingTransaction,
            onDismiss = {
                showSheet = false
                editingTransaction = null
            },
            onDeleteClick = { transactionToDel ->
                scope.launch {
                    dao.delTransactionsById(listOf(transactionToDel.id))
                    selectedIds = emptySet()
                }
                showSheet = false
                editingTransaction = null
            },
            onConfirm = { amountResult, isIncomeResult, categoryResult, commentResult, dateResult ->
                val isEditing = editingTransaction != null
                val idToSave = editingTransaction?.id ?: 0
                val createdAtTime = editingTransaction?.createdAt ?: System.currentTimeMillis()

                val setTransaction = TransactionEntity(
                    id = idToSave,
                    amount = amountResult,
                    category = categoryResult.name,
                    comment = commentResult,
                    date = dateResult,
                    isIncome = isIncomeResult,
                    createdAt = createdAtTime
                )
                scope.launch {
                    if (isEditing) {
                        dao.updateTransaction(setTransaction)
                    } else {
                        dao.insertTransaction(setTransaction)
                    }
                }
                showSheet = false
                editingTransaction = null
            }
        )
    }
    if (showSortSheet) {
        SortBottomSheet(
            currentSortType = currentSortType,
            onDismiss = {
                showSortSheet = false
            },
            onSortSelected = { newSortType ->
                currentSortType = newSortType
            }
        )
    }
}