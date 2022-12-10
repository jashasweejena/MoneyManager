package com.example.moneymanager.ui.addexpenses

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.moneymanager.MoneyManagerApp
import com.example.moneymanager.db.MoneyManagerDataBase
import com.example.moneymanager.data.ExpenseType
import com.example.moneymanager.data.ExpenseUtils
import com.example.moneymanager.databinding.LayoutMoneyAddBottomsheetBinding
import com.example.moneymanager.ui.base.ParentBottomsheetFragment
import com.example.moneymanager.ui.expenseslist.ExpensesRepository
import com.example.moneymanager.ui.utils.setUpdateDbTrigger

class MoneyAddBottomSheet : ParentBottomsheetFragment() {
    private lateinit var binding: LayoutMoneyAddBottomsheetBinding
    private lateinit var appDb: MoneyManagerDataBase
    private val expenseUtils by lazy {
        ExpenseUtils(appDb.getExpenseDao())
    }
    private val viewModel: AddExpenseViewModel by viewModels {
        AddExpenseViewModel.provideFactory(
            ExpensesRepository(appDb.getExpenseDao(), expenseUtils),
            this
        )
    }

    companion object {
        fun start(): MoneyAddBottomSheet {
            return MoneyAddBottomSheet()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appDb = (activity?.applicationContext as MoneyManagerApp).expensesDb
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
            else -> null
        }
        type ?: return

        viewModel.addExpense(
            type,
            binding.etAmount.text.toString().toDoubleOrNull() ?: 0.0,
            binding.etLabel.text.toString()
        )
        setUpdateDbTrigger(reloadFromDb = true, fragmentManager = childFragmentManager)
        dismiss()

    }

    override fun updateUiFromDb() = Unit

}