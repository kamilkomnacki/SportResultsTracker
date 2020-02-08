package com.komnacki.sportresultstracker.formatters

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.IAxisValueFormatter


class TimeAxisValueFormatter : IAxisValueFormatter {
    override fun getFormattedValue(value: Float, axis: AxisBase?): String {
        val time = value.toLong()
        if ((time == Long.MIN_VALUE) || time <= 0L)
            return ""

        val stringValue = StringBuilder(time.toString())

        when (val len = stringValue.length) {
            1,2,3 -> {return stringValue.toString() + " ms"}
            4,5 -> {
                stringValue.insert(len-3, ":")
                return stringValue.toString() + " s"
            }
            6,7 -> {
                stringValue.insert(len-3, ":")
                stringValue.insert(len-5, ":")
                return stringValue.toString() + " min"
            }
            8,9 -> {
                stringValue.insert(len-3, ":")
                stringValue.insert(len-5, ":")
                stringValue.insert(len-7, ":")
                return stringValue.toString() + " h"
            }
            else -> {return stringValue.toString()}
        }
    }
}