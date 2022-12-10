package com.example.moneymanager.ui

import android.os.Bundle
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.savedstate.SavedStateRegistryOwner
import com.example.moneymanager.data.Expense
import com.example.moneymanager.data.ExpenseUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ExpensesViewModel(private val repository: ExpensesRepository) : BaseViewModel() {
    var expensesLiveData: LiveData<List<Expense>> = MutableLiveData()
    var totalExpenseLiveData: LiveData<Double> = MutableLiveData()

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
                    return ExpensesViewModel(myRepository) as T
                }
            }
    }

    fun addExpense(expense: Expense) {
        repository.addExpense(expense, viewModelScope)
    }

    fun getExpenses() {
        viewModelScope.launch {
            repository.getExpenses().collect {
                expensesLiveData.mutable.value = it
            }
        }
    }

    fun getTotalExpense(){
        viewModelScope.launch {
            repository.getTotalExpense().collect {
                totalExpenseLiveData.mutable.value = it
            }
        }
    }
}