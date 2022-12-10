package com.example.moneymanager.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.moneymanager.MoneyManagerDataBase
import com.example.moneymanager.data.ExpenseUtils
import com.example.moneymanager.databinding.FragmentExpenseListBinding
import kotlinx.coroutines.launch

class ExpenseListFragment : ParentFragment() {
    private lateinit var binding: FragmentExpenseListBinding
    private val expenseAdapter: ExpensesRecyclerView by lazy {
        ExpensesRecyclerView()
    }
    private lateinit var appDb: MoneyManagerDataBase
    private val expenseUtils by lazy {
        ExpenseUtils(appDb.getExpenseDao())
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
        context?.let {
            appDb = Room.databaseBuilder(it, MoneyManagerDataBase::class.java, "db").build()
        }

        binding.rvExpenses.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = expenseAdapter
        }
        updateUiFromDb()
        binding.btnAdd.setOnClickListener { onAddClick() }

    }

    override fun updateUiFromDb() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    expenseUtils.getBalance().collect {
                        binding.tvTotalAmt.text = it.toString()
                    }
                }
                launch {
                    appDb.getExpenseDao().getExpenses().collect { expenseList ->
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
            }

        }
    }

    private fun onAddClick() {
        childFragmentManager.beginTransaction().add(MoneyAddBottomSheet.start(), "X").commit()
    }
}