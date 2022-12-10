package com.example.moneymanager

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moneymanager.data.Expense
import com.example.moneymanager.data.ExpenseDao

@Database(entities = [Expense::class], version = 3)
abstract class MoneyManagerDataBase : RoomDatabase() {
    abstract fun getExpenseDao(): ExpenseDao
}