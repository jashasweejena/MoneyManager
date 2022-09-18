package com.example.moneymanager.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moneymanager.data.Expense
import com.example.moneymanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val expenseAdapter: ExpensesRecyclerView by lazy {
        ExpensesRecyclerView()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)
         binding.rvExpenses.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = expenseAdapter
        }
        supportFragmentManager.setFragmentResultListener("KEY", this) { _, result ->
            val expense = result.getParcelable<Expense>("expense")
            expenseAdapter.submitList(mutableListOf(expense?.mapToUiModel()))
        }
        binding.btnAdd.setOnClickListener { onAddClick() }
    }

    private fun onAddClick() {
        supportFragmentManager.beginTransaction().add(MoneyAddBottomSheet.start(), "X").commit()
    }
}