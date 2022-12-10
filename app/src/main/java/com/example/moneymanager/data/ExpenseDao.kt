package com.example.moneymanager.data

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Query("SELECT * FROM expense")
    fun getExpenses(): Flow<List<Expense>>
    @Insert
    suspend fun addExpense(expense: Expense)
    @Delete
    suspend fun removeExpense(expense: Expense)
    @Update
    suspend fun updateExpense(expense: Expense)
}