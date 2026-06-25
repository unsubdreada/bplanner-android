package com.unsubdreada.myapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "budgets")
data class BudgetEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val category: String,
    val limitAmount: Double,
    val currencyCode: String,
    val month: String
)