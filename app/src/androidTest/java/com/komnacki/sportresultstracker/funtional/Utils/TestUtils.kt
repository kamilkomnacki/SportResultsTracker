package com.komnacki.sportresultstracker.funtional.Utils

import android.support.v7.widget.RecyclerView

class TestUtils {
    companion object {
        const val timeIntervalMillis: Long = 200
        const val userName = "Kamil"
        const val sportName = "Rower"
        const val NUMBER_OF_CHARTS = 2

        fun getSizeOfRecyclerView(recyclerView: RecyclerView): Int {
            val adapter = recyclerView.adapter
            return adapter.itemCount
        }
    }
}