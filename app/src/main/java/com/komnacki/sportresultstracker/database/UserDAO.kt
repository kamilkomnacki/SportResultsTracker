package com.komnacki.sportresultstracker.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query


@Dao
interface UserDAO {
    @Insert
    fun insert(user: User)

    @Query("SELECT * FROM " + UserConsts.TABLE_NAME)
    fun getAll(): LiveData<List<User>>

    @Delete
    fun delete(user: User)


}