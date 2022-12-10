package com.example.moneymanager.ui.base

import android.os.Bundle
import android.view.View
import com.example.moneymanager.ui.utils.Constants
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class ParentBottomsheetFragment : BottomSheetDialogFragment() {
    abstract fun updateUiFromDb()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFragmentResultListeners()
    }
    private fun setupFragmentResultListeners() {
        activity?.supportFragmentManager?.setFragmentResultListener(Constants.DB_UPDATE_RESULT_LISTENER, viewLifecycleOwner) { _, result ->
            if (result.containsKey(Constants.SHOULD_FETCH_FROM_DB)) {
                updateUiFromDb()
            }
        }
    }
}