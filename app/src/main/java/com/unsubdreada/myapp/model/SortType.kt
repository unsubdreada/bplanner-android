package com.unsubdreada.myapp.model

import TablerArrowBadgeDown
import TablerArrowBadgeUp
import TablerCalendar
import TablerPencil
import androidx.compose.ui.graphics.vector.ImageVector

enum class SortType(
    val title: String,
    val leadingIcon: ImageVector,
    val trailingIcon: ImageVector
) {
    // Дата записи - сначала новые:
    CREATED_DESC("Дата записи", TablerPencil, TablerArrowBadgeDown),

    // Дата записи - сначала старые:
    CREATED_ASC("Дата записи", TablerPencil, TablerArrowBadgeUp),

    // Дата транзакции - сначала новые:
    TRANSACTION_DESC("Дата транзакции", TablerCalendar, TablerArrowBadgeDown),

    // Дата транзакции - сначала старые:
    TRANSACTION_ASC("Дата транзакции", TablerCalendar, TablerArrowBadgeUp);

    fun toggleSort(): SortType {
        return when (this) {
            CREATED_DESC -> CREATED_ASC
            CREATED_ASC -> CREATED_DESC
            TRANSACTION_DESC -> TRANSACTION_ASC
            TRANSACTION_ASC -> TRANSACTION_DESC
        }
    }
}