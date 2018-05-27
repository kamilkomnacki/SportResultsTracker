package com.komnacki.sportresultstracker.formatters

import android.util.Log
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import java.math.BigDecimal
import java.math.MathContext
import kotlin.math.abs
import kotlin.math.roundToLong


class DistanceAxisValueFormatter : IAxisValueFormatter {
    override fun getFormattedValue(value: Float, axis: AxisBase?): String {
        Log.d("DISTANCE FORMATER: ", " value: ${value}")
        if(abs(value) < 1000.0f) {
            Log.d("DISTANCE FORMATER: ", " value<=1: ${(value * 1000f).roundToLong()} m")
            return value.toString() + "m"
        }else {
            Log.d("DISTANCE FORMATER: ", " value>11: ${value}km")
            var output: String = value
                    .toBigDecimal()
                    .divide(BigDecimal.valueOf(1000))
                    .setScale(3, BigDecimal.ROUND_HALF_UP)
                    .toString()

            return "$output km"
        }
    }


}