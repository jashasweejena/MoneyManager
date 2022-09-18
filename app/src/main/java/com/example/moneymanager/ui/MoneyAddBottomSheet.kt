package com.example.moneymanager.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.moneymanager.data.Expense
import com.example.moneymanager.data.ExpenseType
import com.example.moneymanager.databinding.LayoutMoneyAddBottomsheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MoneyAddBottomSheet : BottomSheetDialogFragment() {
    private lateinit var binding: LayoutMoneyAddBottomsheetBinding
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
        val expense = Expense(System.currentTimeMillis().toString(), type, binding.etAmount.text.toString().toDoubleOrNull() ?: 0.0, binding.etLabel.text.toString(), System.currentTimeMillis())
        parentFragmentManager.setFragmentResult("KEY", bundleOf("expense" to expense))
        dismiss()
    }
}