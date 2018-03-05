package com.komnacki.sportresultstracker.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = SportConsts.TABLE_NAME,
        foreignKeys = [(ForeignKey(
                entity = User::class,
                parentColumns = [(UserConsts.ID)],
                childColumns = [(SportConsts.USER_ID)],
                onDelete = ForeignKey.CASCADE))])
class Sport {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = SportConsts.ID)
    var id: Long? = null

    @ColumnInfo(name = SportConsts.USER_ID)
    var user_id: Long? = null

    @ColumnInfo(name = SportConsts.NAME)
    var name: String? = null

    @ColumnInfo(name = SportConsts.NAME)
    var hasTime: String? = null

    @ColumnInfo(name = SportConsts.RECORDS_AMOUNT)
    var recordsAmount: Long? = null


}