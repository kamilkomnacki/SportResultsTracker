package com.komnacki.sportresultstracker.database

import android.arch.persistence.room.*
import java.util.*


@Entity(tableName = RecordConsts.TABLE_NAME,
        foreignKeys = [(ForeignKey(
                entity = Sport::class,
                parentColumns = [(SportConsts.ID)],
                childColumns = [(RecordConsts.SPORT_ID)],
                onDelete = ForeignKey.CASCADE))])
class Record {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = RecordConsts.ID)
    var id: Long? = null

    @ColumnInfo(name = RecordConsts.SPORT_ID)
    var sport_id: Long? = null

    @ColumnInfo(name = RecordConsts.DATE)
    @TypeConverters(Converters::class)
    var date: Date? = null

    @ColumnInfo(name = RecordConsts.DISTANCE)
    var distance: Long? = null

    @ColumnInfo(name = RecordConsts.TIME)
    var time: Long? = null

    @ColumnInfo(name = RecordConsts.REPEAT)
    var repeat: Long? = null

    @ColumnInfo(name = RecordConsts.WEIGHT)
    var weight: Long? = null
}