package com.komnacki.sportresultstracker.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface RecordDAO {
    @Insert
    fun insert(record: Record)

    @Query("SELECT * FROM " + RecordConsts.TABLE_NAME)
    fun getAll(): List<Record>
}