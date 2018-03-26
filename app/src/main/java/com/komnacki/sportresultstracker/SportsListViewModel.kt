package com.komnacki.sportresultstracker

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.komnacki.sportresultstracker.database.Sport
import com.komnacki.sportresultstracker.database.SportRepository


class SportsListViewModel : ViewModel() {

    private var sportRepository = SportRepository()
    private var listOfSports = sportRepository.getAll(286)

    fun insert(sport: Sport) {
        sportRepository.insert(sport)
    }

    fun getSportsList(): LiveData<List<Sport>> {
        return listOfSports
    }
}