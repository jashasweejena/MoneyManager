package com.example.moneymanager.ui.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.moneymanager.ui.utils.Constants.SHOULD_FETCH_FROM_DB

fun Fragment.setUpdateDbTrigger(fragmentManager: FragmentManager, reloadFromDb: Boolean) {
    val bundle = Bundle().apply {
        putBoolean(SHOULD_FETCH_FROM_DB, reloadFromDb)
    }
    activity?.supportFragmentManager?.setFragmentResult(Constants.DB_UPDATE_RESULT_LISTENER, bundle)
}