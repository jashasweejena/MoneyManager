package com.example.moneymanager.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moneymanager.data.ExpenseUiModel
import com.example.moneymanager.databinding.ItemExpenseBinding

class ExpensesRecyclerView : ListAdapter<ExpenseUiModel, ExpenseViewHolder>(ExpensesComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val binding = ItemExpenseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExpenseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class ExpensesComparator : DiffUtil.ItemCallback<ExpenseUiModel>() {
    override fun areItemsTheSame(oldItem: ExpenseUiModel, newItem: ExpenseUiModel): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: ExpenseUiModel, newItem: ExpenseUiModel): Boolean {
        return oldItem.id == newItem.id
    }

}

class ExpenseViewHolder(private val binding: ItemExpenseBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(expense: ExpenseUiModel) {
        binding.tvAmount.text = expense.amount.toString()
        binding.tvLabel.text = expense.label
        binding.tvTime.text = expense.time
    }
}