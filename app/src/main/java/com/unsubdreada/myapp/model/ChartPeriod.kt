package com.unsubdreada.myapp.model

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

enum class ChartPeriod(val title: String) {
    WEEK("Неделя"),
    MONTH("Месяц"),
    YEAR("Год"),
    ALL("Всё время"),
    CUSTOM("Свой период");

    // дата в формате "yyyy-MM-dd" для фильтрации в БД
    fun startDate(): String? {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val calendar = Calendar.getInstance()
        return when (this) {
            WEEK -> {
                calendar.add(Calendar.DAY_OF_YEAR, -7); formatter.format(calendar.time)
            }

            MONTH -> {
                calendar.add(Calendar.MONTH, -1); formatter.format(calendar.time)
            }

            YEAR -> {
                calendar.add(Calendar.YEAR, -1); formatter.format(calendar.time)
            }

            ALL, CUSTOM -> null
        }
    }

    fun dateRangeLabel(customStart: String? = null, customEnd: String? = null): String {
        if (this == ALL) return "Всё время"
        if (this == CUSTOM) {
            if (customStart == null || customEnd == null) return "Свой период"
            val dbFmt = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val labelFmt = SimpleDateFormat("d MMMM", Locale("ru"))
            val start = dbFmt.parse(customStart) ?: return "Свой период"
            val end = dbFmt.parse(customEnd) ?: return "Свой период"
            return "${labelFmt.format(start)} — ${labelFmt.format(end)}"
        }
        val labelFmt = SimpleDateFormat("d MMMM", Locale("ru"))
        val today = Calendar.getInstance()
        val start = Calendar.getInstance()
        when (this) {
            WEEK -> start.add(Calendar.DAY_OF_YEAR, -7)
            MONTH -> start.add(Calendar.MONTH, -1)
            YEAR -> start.add(Calendar.YEAR, -1)
            else -> {}
        }
        return "${labelFmt.format(start.time)} — ${labelFmt.format(today.time)}"
    }
}
