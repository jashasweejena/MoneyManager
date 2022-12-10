package com.example.moneymanager

import android.app.Application
import androidx.room.Room

class MoneyManagerApp : Application() {
    lateinit var expensesDb: MoneyManagerDataBase

    override fun onCreate() {
        expensesDb = Room.databaseBuilder(this, MoneyManagerDataBase::class.java, "db").build()
        super.onCreate()
    }
}