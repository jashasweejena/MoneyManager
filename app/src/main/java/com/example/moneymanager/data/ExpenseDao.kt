package com.example.moneymanager.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ExpenseDao {
    @Query("SELECT * FROM expense")
    fun getExpenses(): LiveData<List<Expense>>
    @Insert
    fun addExpense(expense: Expense)
    @Delete
    fun removeExpense(expense: Expense)
    @Update
    fun updateExpense(expense: Expense)
}