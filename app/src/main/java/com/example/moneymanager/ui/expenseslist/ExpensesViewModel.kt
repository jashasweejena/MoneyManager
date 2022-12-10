package com.example.moneymanager.ui.expenseslist

import android.os.Bundle
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.example.moneymanager.data.Expense
import com.example.moneymanager.viewmodel.BaseViewModel
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