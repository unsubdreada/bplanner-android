package com.unsubdreada.myapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unsubdreada.myapp.model.SortType
import com.unsubdreada.myapp.ui.theme.PrimaryDark
import com.unsubdreada.myapp.ui.theme.TextPrimary
import com.unsubdreada.myapp.ui.theme.TextSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortBottomSheet(
    currentSortType: SortType,
    onDismiss: () -> Unit,
    onSortSelected: (SortType) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val displayItems = remember(currentSortType) {
        val createdItem =
            if (currentSortType == SortType.CREATED_ASC) SortType.CREATED_ASC else SortType.CREATED_DESC
        val transactionItem =
            if (currentSortType == SortType.TRANSACTION_ASC) SortType.TRANSACTION_ASC else SortType.TRANSACTION_DESC
        listOf(createdItem, transactionItem)
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = PrimaryDark,
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
        ) {
            Text(
                text = "Сортировка",
                color = TextPrimary,
                fontSize = 15.sp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                color = Color.DarkGray.copy(0.2f),
                thickness = 3.dp
            )
            displayItems.forEach { item ->
                val isSelected = when (item) {
                    SortType.CREATED_ASC, SortType.CREATED_DESC -> currentSortType == SortType.CREATED_ASC || currentSortType == SortType.CREATED_DESC
                    SortType.TRANSACTION_ASC, SortType.TRANSACTION_DESC -> currentSortType == SortType.TRANSACTION_ASC || currentSortType == SortType.TRANSACTION_DESC
                }
                val targetTypeClick = if (isSelected) currentSortType.toggleSort() else item

                Row(
                    modifier = Modifier
                        .clickable { onSortSelected(targetTypeClick) }
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = item.leadingIcon,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                            tint = if (isSelected) TextPrimary else TextSecondary
                        )
                        Spacer(Modifier.width(10.dp))
                        Text(
                            text = item.title,
                            fontSize = 16.sp,
                            color = if (isSelected) TextPrimary else TextSecondary
                        )
                    }
                    Spacer(Modifier.width(10.dp))
                    Icon(
                        imageVector = if (isSelected) currentSortType.trailingIcon else item.trailingIcon,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                        tint = if (isSelected) TextPrimary else TextSecondary
                    )
                }
            }
        }
    }
}

