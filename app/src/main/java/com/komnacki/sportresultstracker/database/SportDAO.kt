package com.komnacki.sportresultstracker.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface SportDAO {
    @Insert
    fun insert(sport: Sport)

    @Query("SELECT * FROM " + SportConsts.TABLE_NAME)
    fun getAll(): List<Sport>


}