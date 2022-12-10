package com.example.moneymanager.ui

import com.example.moneymanager.data.Expense
import com.example.moneymanager.data.ExpenseDao
import com.example.moneymanager.data.ExpenseUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ExpensesRepository(private val expenseDao: ExpenseDao, private val expenseUtils: ExpenseUtils) {
    fun getExpenses(): Flow<List<Expense>> = expenseDao.getExpenses()
    fun addExpense(expense: Expense, viewModelScope: CoroutineScope) {
        viewModelScope.launch {
            expenseDao.addExpense(expense)
        }
    }
    fun getTotalExpense(): Flow<Double> = expenseUtils.getBalance()
}