package com.example.moneymanager.ui.addexpenses

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import com.example.moneymanager.data.Expense
import com.example.moneymanager.data.ExpenseType
import com.example.moneymanager.viewmodel.BaseViewModel
import com.example.moneymanager.ui.expenseslist.ExpensesRepository

class AddExpenseViewModel(private val repository: ExpensesRepository) : BaseViewModel() {
    companion object {
        fun provideFactory(
            myRepository: ExpensesRepository,
            owner: SavedStateRegistryOwner,
            defaultArgs: Bundle? = null,
        ): AbstractSavedStateViewModelFactory =
            object : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(
                    key: String,
                    modelClass: Class<T>,
                    handle: SavedStateHandle
                ): T {
                    return AddExpenseViewModel(myRepository) as T
                }
            }
    }

    fun addExpense(type: ExpenseType, amount: Double, label: String) {
        val multiplier = when(type) {
            ExpenseType.INCOME -> 1.0
            ExpenseType.EXPENDITURE -> -1.0
        }
        val expense = Expense(type = type, amount = amount * multiplier, label = label, timeInMillis = System.currentTimeMillis())
        repository.addExpense(expense, viewModelScope)
    }
}