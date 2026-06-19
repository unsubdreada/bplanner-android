package com.unsubdreada.myapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val amount: Double,
    val category: String,
    val comment: String,
    val date: String,
    val isIncome: Boolean,
    val createdAt: Long = System.currentTimeMillis(),
    val currencyCode: String = "RUB"
)