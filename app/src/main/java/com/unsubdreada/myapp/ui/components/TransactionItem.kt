package com.unsubdreada.myapp.ui.components

import TablerCheck
import TablerChecks
import TablerClock
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unsubdreada.myapp.data.TransactionEntity
import com.unsubdreada.myapp.model.FinanceCategory
import com.unsubdreada.myapp.ui.theme.PrimaryDark
import com.unsubdreada.myapp.ui.theme.TextExpense
import com.unsubdreada.myapp.ui.theme.TextIncome
import com.unsubdreada.myapp.ui.theme.TextPrimary
import com.unsubdreada.myapp.ui.theme.TextSecondary
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun TransactionItem(
    transaction: TransactionEntity,
    category: FinanceCategory,
    isSelectedMode: Boolean,
    onLongClick: () -> Unit,
    onClick: () -> Unit
) {
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
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .combinedClickable(
                onLongClick = onLongClick,
                onClick = onClick
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
                        if (!isSelectedMode) {
                            category.color
                        } else {
                            category.color.copy(alpha = 0.5f)
                        }
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = if (!isSelectedMode) {
                        category.icon
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
                text = category.title,
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