package com.komnacki.sportresultstracker.formatters

import android.util.Log
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import kotlin.math.abs


class TimeAxisValueFormatter : IAxisValueFormatter {
    override fun getFormattedValue(value: Float, axis: AxisBase?): String {
        Log.d("TimeAxis: ", " input value: $value")
        var time = value.toLong()
        Log.d("TimeAxis: ", " input time: $time")
        if ((time == Long.MIN_VALUE) || time <= 0L)
            return ""

        val stringValue = StringBuilder(time.toString())
        var len = stringValue.length


        if(len>=5 && (stringValue[len-5].toInt()-48) > 5) {
//            var rem = abs((stringValue[len-5].toInt()-48) - 6)
//            stringValue[len - 5] = rem.toChar()
//            for(i in stringValue.length-5..0){
//                if(stringValue[i]!='9')
//                    stringValue.setCharAt(i , (stringValue[i].toInt()+1).toChar())
//            }
        }
        if(len>=7 && (stringValue[len-7].toInt()-48 > 5)) {
//            var rem = abs((stringValue[len-7].toInt()-48) - 6)
//            stringValue[len - 7] = rem.toChar()
//            for(i in stringValue.length-7 downTo 0){
//                Log.d("TimeAxis: ", " w pętli: i: $i, ${stringValue[i].toInt()}")
//                if(stringValue[i]!='9')
//                    stringValue.setCharAt(0 , (stringValue[i].toInt()+1).toChar())
//            }
        }

        when(len){
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