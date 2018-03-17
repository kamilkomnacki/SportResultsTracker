package com.komnacki.sportresultstracker.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query


@Dao
interface UserDAO {

    @Insert(onConflict = REPLACE)
    fun insert(user: Array<out User?>)

    @Query("SELECT * FROM " + UserConsts.TABLE_NAME)
    fun getAll(): LiveData<List<User>>
}