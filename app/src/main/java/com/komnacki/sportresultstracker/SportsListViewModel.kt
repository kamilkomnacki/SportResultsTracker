package com.komnacki.sportresultstracker

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.komnacki.sportresultstracker.database.Sport
import com.komnacki.sportresultstracker.database.SportRepository


class SportsListViewModel(aplication: Application, userId: Long) : ViewModel() {

    private var sportRepository = SportRepository()
    private var listOfSports = sportRepository.getAll(userId)

    fun insert(sport: Sport) {
        sportRepository.insert(sport)
    }

    fun getSportsList(): LiveData<List<Sport>> {
        return listOfSports
    }
}