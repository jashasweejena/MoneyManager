package com.example.moneymanager.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Expense(
    val id: String,
    val type: ExpenseType,
    val amount: Double,
    val label: String,
    val timeInMillis: Long
) : Parcelable

enum class ExpenseType {
    INCOME,
    EXPENDITURE
}
