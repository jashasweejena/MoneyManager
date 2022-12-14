package com.example.moneymanager.ui.expenseslist

import com.example.moneymanager.data.Expense
import com.example.moneymanager.data.ExpenseUiModel
import java.text.DateFormat

fun Expense.mapToUiModel(): ExpenseUiModel {
    return ExpenseUiModel(
        id.toString(),
        type,
        amount,
        label,
        DateFormat.getTimeInstance().format(timeInMillis)
    )
}