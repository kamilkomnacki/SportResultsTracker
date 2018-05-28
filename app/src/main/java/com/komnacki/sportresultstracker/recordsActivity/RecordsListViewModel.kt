package com.komnacki.sportresultstracker.recordsActivity

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.komnacki.sportresultstracker.database.Record
import com.komnacki.sportresultstracker.database.RecordRepository

class RecordsListViewModel(application: Application, val sportId: Long): ViewModel() {

    private var recordRepository = RecordRepository()
    private var listOfRecords: LiveData<List<Record>> = recordRepository.getAll(sportId)


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
        if(listOfRecords.value == null){
            Log.d("RecViewModel", "listOfRecords is null in getRecordList")
            //listOfRecords = recordRepository.getAll(sportId)
            Log.d("RecViewModel", "listOfRecords: " + listOfRecords.value)
        }
        return listOfRecords
    }
}