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

    fun update(sport: Sport) {
        sportRepository.update(sport)
    }

    fun getSport(id: Long?): Sport{
        for(sport in listOfSports.value.orEmpty()){
            if(sport.id == id)
                return sport
        }
        return Sport()
    }

    fun getSportsList(): LiveData<List<Sport>> {
        return listOfSports
    }

    fun isNameExist(name: String): Boolean{
        for(sport in listOfSports.value.orEmpty()){
            if(sport.name == name)
                return true
        }
        return false
    }
}