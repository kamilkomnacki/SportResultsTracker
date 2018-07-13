package com.komnacki.sportresultstracker.formatters

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import java.math.BigDecimal
import kotlin.math.abs


class DistanceAxisValueFormatter : IAxisValueFormatter {
    override fun getFormattedValue(value: Float, axis: AxisBase?): String {
        return if (abs(value) < 1000.0f) {
            value.toString() + "m"
        }else {
            var output: String = value
                    .toBigDecimal()
                    .divide(BigDecimal.valueOf(1000))
                    .setScale(3, BigDecimal.ROUND_HALF_UP)
                    .toString()

            "$output km"
        }
    }
}