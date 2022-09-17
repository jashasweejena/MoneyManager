package com.example.moneymanager

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Expense(
    val id: String,
    val type: ExpenseType,
    val amount: Double,
    val label: String,
) : Parcelable

enum class ExpenseType {
    INCOME,
    EXPENDITURE
}
