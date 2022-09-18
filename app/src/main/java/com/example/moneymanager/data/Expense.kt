package com.example.moneymanager.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "expense")
data class Expense(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val type: ExpenseType,
    val amount: Double,
    val label: String,
    val timeInMillis: Long
) : Parcelable

enum class ExpenseType {
    INCOME,
    EXPENDITURE
}
