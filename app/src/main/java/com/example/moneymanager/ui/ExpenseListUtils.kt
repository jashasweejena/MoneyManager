package com.example.moneymanager.ui

import com.example.moneymanager.data.Expense
import com.example.moneymanager.data.ExpenseUiModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun Expense.mapToUiModel(): ExpenseUiModel {
    return ExpenseUiModel(
        id.toString(),
        type,
        amount,
        label,
        DateFormat.getTimeInstance().format(timeInMillis)
    )
}