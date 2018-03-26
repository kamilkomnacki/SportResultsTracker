package com.komnacki.sportresultstracker.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface SportDAO {
    @Insert
    fun insert(sport: Sport)

    @Query("SELECT * FROM " + SportConsts.TABLE_NAME)
    fun getAll(): LiveData<List<Sport>>

    @Query("SELECT * FROM " + SportConsts.TABLE_NAME +
                " WHERE " + SportConsts.USER_ID + " = :userId")
    fun getAll(userId: Long): LiveData<List<Sport>>

    @Delete
    fun delete(sport: Sport)
}