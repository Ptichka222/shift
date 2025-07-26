package com.example.shiftscedule.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ShiftViewModelFactory(private val application: Application) : ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShiftViewModel::class.java)) {
            return ShiftViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
