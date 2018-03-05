package com.komnacki.sportresultstracker.database

import android.arch.persistence.room.ColumnInfo

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.util.TableInfo
import java.sql.Date

@Entity(tableName = UserConsts.TABLE_NAME)
class User {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = UserConsts.ID)
    var id: Long? = null

    @ColumnInfo(name = UserConsts.NAME)
    var name: String? = null

    @ColumnInfo(name = UserConsts.SPORTS_AMOUNT)
    var sportsAmount: Long? = null
}