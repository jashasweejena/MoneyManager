package com.example.moneymanager.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.moneymanager.MoneyManagerDataBase
import com.example.moneymanager.data.Expense
import com.example.moneymanager.data.ExpenseType
import com.example.moneymanager.databinding.LayoutMoneyAddBottomsheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoneyAddBottomSheet : BottomSheetDialogFragment() {
    private lateinit var binding: LayoutMoneyAddBottomsheetBinding
    private val appDb: MoneyManagerDataBase? by lazy {
        context?.applicationContext?.let { Room.databaseBuilder(it, MoneyManagerDataBase::class.java, "db").build() }
    }
    companion object {
        fun start(): MoneyAddBottomSheet {
            return MoneyAddBottomSheet()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LayoutMoneyAddBottomsheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.btnAdd.setOnClickListener { onAddClick() }
    }

    private fun onAddClick() {
        val type = when {
            binding.radioIncome.isChecked -> ExpenseType.INCOME
            binding.radioExpenditure.isChecked -> ExpenseType.EXPENDITURE
            else -> ExpenseType.INCOME
        }
        val expense = Expense(type = type, amount = binding.etAmount.text.toString().toDoubleOrNull() ?: 0.0, label = binding.etLabel.text.toString(), timeInMillis = System.currentTimeMillis())
        lifecycleScope.launch(Dispatchers.IO) {
            appDb?.getExpenseDao()?.addExpense(expense)
        }
        parentFragmentManager.setFragmentResult("KEY", bundleOf("expense" to expense))
        dismiss()
    }
}