package com.komnacki.sportresultstracker

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider


class SportsListFactory(
        var application: Application,
        var userId: Long
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SportsListViewModel(application, userId) as T
    }
}