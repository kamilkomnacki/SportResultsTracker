package com.komnacki.sportresultstracker.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface RecordDAO {
    @Insert
    fun insert(record: Record)

    @Update
    fun update(record: Record)

    @Delete
    fun delete(record: Record)

    @Query("SELECT * FROM " + RecordConsts.TABLE_NAME +
            " WHERE " + RecordConsts.ID + " = :id")
    fun get(id: Long): LiveData<Record>

    @Query("SELECT * FROM " + RecordConsts.TABLE_NAME)
    fun getAll(): LiveData<List<Record>>

    @Query("SELECT * FROM " + RecordConsts.TABLE_NAME +
            " WHERE " + RecordConsts.SPORT_ID + " = :sportId")
    fun getAll(sportId: Long): LiveData<List<Record>>
}