package com.example.moneymanager.data

data class ExpenseUiModel(
    val id: String,
    val type: ExpenseType,
    val amount: Double,
    val label: String,
    val time: String
)

