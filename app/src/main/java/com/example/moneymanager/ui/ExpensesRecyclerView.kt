package com.example.moneymanager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moneymanager.databinding.ItemExpenseBinding

class ExpensesRecyclerView : ListAdapter<Expense, ExpenseRecyclerViewHolder>(ExpensesComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseRecyclerViewHolder {
        val binding = ItemExpenseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExpenseRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExpenseRecyclerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class ExpensesComparator : DiffUtil.ItemCallback<Expense>() {
    override fun areItemsTheSame(oldItem: Expense, newItem: Expense): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Expense, newItem: Expense): Boolean {
        return oldItem.id == newItem.id
    }

}

class ExpenseRecyclerViewHolder(private val binding: ItemExpenseBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(expense: Expense) {
        binding.tvAmount.text = expense.amount.toString()
        binding.tvLabel.text = expense.label
    }
}