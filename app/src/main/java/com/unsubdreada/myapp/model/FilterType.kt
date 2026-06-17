package com.unsubdreada.myapp.model

import TablerInfinity
import TablerMoneybagMinus
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.unsubdreada.myapp.ui.theme.ButtonDisabledBackground
import com.unsubdreada.myapp.ui.theme.ButtonExpenseBackground
import com.unsubdreada.myapp.ui.theme.ButtonIncomeBackground
import com.unsubdreada.myapp.ui.theme.icons.TablerMoneybagPlus

enum class FilterType(
    val title: String,
    val icon: ImageVector,
    val color: Color
) {
    ALL("Все", TablerInfinity, ButtonDisabledBackground),
    EXPENSE("Расходы", TablerMoneybagMinus, ButtonExpenseBackground),
    INCOME("Доходы", TablerMoneybagPlus, ButtonIncomeBackground);

    fun next(): FilterType {
        return when (this) {
            ALL -> EXPENSE
            EXPENSE -> INCOME
            INCOME -> ALL
        }
    }
}