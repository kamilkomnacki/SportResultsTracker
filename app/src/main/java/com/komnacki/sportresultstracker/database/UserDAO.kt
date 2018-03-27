package com.komnacki.sportresultstracker.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*


@Dao
interface UserDAO {
    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Query("SELECT * FROM " + UserConsts.TABLE_NAME)
    fun getAll(): LiveData<List<User>>

    @Delete
    fun delete(user: User)


}