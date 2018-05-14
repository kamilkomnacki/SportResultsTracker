package com.komnacki.sportresultstracker

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.komnacki.sportresultstracker.database.Record
import com.komnacki.sportresultstracker.database.RecordRepository
import com.komnacki.sportresultstracker.database.Sport

class RecordsListViewModel(application: Application, sportId: Long): ViewModel() {

    private var recordRepository = RecordRepository()
    private var listOfRecords = recordRepository.getAll(sportId)

    fun insert(record: Record) {
        recordRepository.insert(record)
    }

    fun update(record: Record) {
        recordRepository.update(record)
    }

    fun getRecord(id: Long?): Record {
        for(record in listOfRecords.value.orEmpty()){
            if(record.id == id)
                return record
        }
        return Record()
    }

    fun getRecordList(): LiveData<List<Record>> {
        return listOfRecords
    }

}