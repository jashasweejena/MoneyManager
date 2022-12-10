package com.example.moneymanager.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class ParentFragment : Fragment() {
    abstract fun updateUiFromDb()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFragmentResultListeners()
    }
    private fun setupFragmentResultListeners() {
        activity?.supportFragmentManager?.setFragmentResultListener(Constants.DB_UPDATE_RESULT_LISTENER, viewLifecycleOwner) {_, result ->
            if (result.containsKey(Constants.SHOULD_FETCH_FROM_DB)) {
                updateUiFromDb()
            }
        }
    }
}