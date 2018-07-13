package com.komnacki.sportresultstracker.sportsActivity

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider


class SportsListFactory(var userId: Long)
    : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SportsListViewModel(userId) as T
    }
}