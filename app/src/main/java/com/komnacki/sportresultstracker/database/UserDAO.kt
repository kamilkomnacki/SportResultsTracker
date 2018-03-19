package com.komnacki.sportresultstracker.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE


@Dao
interface UserDAO {

    @Insert
    fun insert(user: User)

    @Query("DELETE FROM " + UserConsts.TABLE_NAME)
    fun deleteAll()

    @Query("SELECT * FROM " + UserConsts.TABLE_NAME)
    fun getAll(): LiveData<List<User>>
}