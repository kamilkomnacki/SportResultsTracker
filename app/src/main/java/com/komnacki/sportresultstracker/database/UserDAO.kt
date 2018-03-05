package com.komnacki.sportresultstracker.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.util.TableInfo


@Dao
interface UserDAO {

    @Insert
    fun insert(user: User)

    @Query("SELECT * FROM " + UserConsts.TABLE_NAME)
    fun getAll(): List<User>
}