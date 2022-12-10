package com.example.moneymanager.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.math.exp

class ExpenseUtils(private val expenseDao: ExpenseDao) {

    fun getBalance(): Flow<Double> {
        return expenseDao.getExpenses().map { expenseList ->
            expenseList.sumOf { it.amount }
        }
    }
}