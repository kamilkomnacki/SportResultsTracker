package com.komnacki.sportresultstracker.recordsActivity

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider


class RecordsListFactory(
        var application: Application,
        var sportId: Long
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RecordsListViewModel(application, sportId) as T
    }
}