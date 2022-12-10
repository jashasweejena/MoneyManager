package com.example.moneymanager.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moneymanager.MoneyManagerApp
import com.example.moneymanager.MoneyManagerDataBase
import com.example.moneymanager.data.ExpenseUtils
import com.example.moneymanager.databinding.FragmentExpenseListBinding

class ExpenseListFragment : ParentFragment() {
    private lateinit var binding: FragmentExpenseListBinding
    private val expenseAdapter: ExpensesRecyclerView by lazy {
        ExpensesRecyclerView()
    }
    private lateinit var appDb: MoneyManagerDataBase
    private val expenseUtils by lazy {
        ExpenseUtils(appDb.getExpenseDao())
    }

    private val viewModel: ExpensesViewModel by viewModels {
        ExpensesViewModel.provideFactory(ExpensesRepository(appDb.getExpenseDao(), expenseUtils), this)
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
        binding = FragmentExpenseListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvExpenses.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = expenseAdapter
        }
        setupObservers()
        updateUiFromDb()
        binding.btnAdd.setOnClickListener { onAddClick() }

    }

    private fun setupObservers() {
        viewModel.totalExpenseLiveData.observe(viewLifecycleOwner) {
            binding.tvTotalAmt.text = it.toString()
        }

        viewModel.expensesLiveData.observe(viewLifecycleOwner) { expenseList ->
            expenseAdapter.submitList(expenseList.map { it.mapToUiModel() }.toList())
            if (expenseAdapter.itemCount > 0) {
                binding.rvExpenses.postDelayed({
                    binding.rvExpenses.smoothScrollToPosition(
                        expenseAdapter.itemCount - 1
                    )
                }, 200)
            }
        }
    }

    override fun updateUiFromDb() {
        viewModel.getExpenses()
        viewModel.getTotalExpense()
    }

    private fun onAddClick() {
        childFragmentManager.beginTransaction().add(MoneyAddBottomSheet.start(), "X").commit()
    }
}