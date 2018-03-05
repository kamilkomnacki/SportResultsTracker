package com.komnacki.sportresultstracker.database

import android.arch.persistence.room.TypeConverter
import java.util.*


class Converters {
    @TypeConverter
    fun fromTimestampToDate(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun fromDateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}