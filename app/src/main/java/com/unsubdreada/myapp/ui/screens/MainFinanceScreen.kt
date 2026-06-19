package com.unsubdreada.myapp.ui.screens

import TablerCurrencyRubel
import TablerPlusMinus
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.unsubdreada.myapp.data.AppDatabase
import com.unsubdreada.myapp.data.TransactionEntity
import com.unsubdreada.myapp.model.CurrencyType
import com.unsubdreada.myapp.model.FilterType
import com.unsubdreada.myapp.model.FinanceCategory
import com.unsubdreada.myapp.model.SortType
import com.unsubdreada.myapp.ui.components.Header
import com.unsubdreada.myapp.ui.components.SortBottomSheet
import com.unsubdreada.myapp.ui.components.TransactionBottomSheet
import com.unsubdreada.myapp.ui.components.TransactionItem
import com.unsubdreada.myapp.ui.theme.ButtonAcceptBackground
import com.unsubdreada.myapp.ui.theme.ButtonAcceptText
import com.unsubdreada.myapp.ui.theme.TextPrimary
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainFinanceScreen(
    innerPadding: PaddingValues,
    defaultCurrency: String
) {
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

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                state = scrollState,
                modifier = Modifier
                    .fillMaxSize(),
//                contentPadding = innerPadding,
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                stickyHeader {
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
                }
                items(
                    items = transactionList,
                    key = { transaction -> transaction.id }
                ) { transaction ->
                    val category = remember(transaction.category) {
                        runCatching { FinanceCategory.valueOf(transaction.category) }
                            .getOrElse { FinanceCategory.OTHER_EXP }
                    }
                    val isCurrentSelect = selectedIds.contains(transaction.id)
                    val transactionCurrencySymbol = remember(transaction.currencyCode) {
                        runCatching { CurrencyType.valueOf(transaction.currencyCode).symbol }.getOrElse { TablerCurrencyRubel }
                    }


                    TransactionItem(
                        transaction = transaction,
                        isSelectedMode = isCurrentSelect,
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
                                editingTransaction = transaction
                                showSheet = true
                            }
                        },
                        category = category,
                        currencySymbol = transactionCurrencySymbol
                    )
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
    if (showSheet) {
        TransactionBottomSheet(
            transactionEdit = editingTransaction,
            defaultCurrency = defaultCurrency,
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
            onConfirm = { amountResult, isIncomeResult, categoryResult, commentResult, dateResult, currencyResult ->
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
                    createdAt = createdAtTime,
                    currencyCode = currencyResult
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