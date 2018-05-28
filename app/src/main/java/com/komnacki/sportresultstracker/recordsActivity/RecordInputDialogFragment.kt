package com.komnacki.sportresultstracker.recordsActivity

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import com.komnacki.sportresultstracker.R
import com.komnacki.sportresultstracker.database.Record
import kotlinx.android.synthetic.main.alert_dialog_record_input.*
import kotlinx.android.synthetic.main.alert_dialog_record_input.view.*
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*


class RecordInputDialogFragment : DialogFragment() {
    val LOG_TAG = RecordInputDialogFragment::class.java.name


    companion object {
        private val ARG_TITLE: String = "TITLE"

        private lateinit var record: Record
        private lateinit var recordsListViewModel: RecordsListViewModel

        fun newInstance(title: String, rec: Record, recListViewModel: RecordsListViewModel): RecordInputDialogFragment {
            record = rec
            recordsListViewModel = recListViewModel
            var fragment = RecordInputDialogFragment()
            val args = Bundle()
            args.putString(ARG_TITLE, title)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val title = arguments.getString(ARG_TITLE)
        val alert = AlertDialog.Builder(activity)
        val viewInflated = LayoutInflater
                .from(activity)
                .inflate(R.layout.alert_dialog_record_input,
                        sv_alert_dialog_record_edit,
                        false)

        val etTime = viewInflated.et_time_recordEdit
        val etDistance = viewInflated.et_distance_recordEdit
        val calendar = viewInflated.calendarEdit

        if (record.id != null) {
            calendar.date = record.date!!.time

            if (!record.time!!.equals(Long.MIN_VALUE))
                etTime.setText(getTimeFromDatabaseLongValue(record.time!!))

            if (!record.distance!!.equals(Long.MIN_VALUE)) {
                val distanceBigDecimal: BigDecimal = record.distance!!
                        .toBigDecimal()
                        .movePointLeft(3)
                etDistance.setText(distanceBigDecimal.toPlainString())
            }
        }

        var date: java.util.Date = java.util.Date(calendar.date)

        calendar.setOnDateChangeListener { calendarView, year, month, day ->
            val newDate = java.util.Date(year - 1900, month, day)

            val timeNow = Calendar.getInstance()
            val offset = timeNow.get(Calendar.ZONE_OFFSET) + timeNow.get(Calendar.DST_OFFSET)
            val currentTime = (timeNow.timeInMillis + offset) % (24 * 60 * 60 * 1000)

            val newDateMilliseconds = newDate.time + currentTime
            date = java.util.Date(newDateMilliseconds)
        }

        alert.setView(viewInflated)
        alert.setTitle(title)

        alert.setPositiveButton(android.R.string.yes) { dialog, position ->
            record.date = date


            val etTimeRec = viewInflated.et_time_recordEdit.text
            if (!fieldIsBlank(etTimeRec))
                record.time = getTimeFromMaskedEditText(viewInflated.et_time_recordEdit.text.toString())
            else
                record.time = Long.MIN_VALUE

            val etDistanceRec = viewInflated.et_distance_recordEdit.text
            if (!fieldIsBlank(etDistanceRec)) {
                var distance = viewInflated.et_distance_recordEdit.text.toString().toBigDecimal()
                distance = distance.setScale(3, RoundingMode.HALF_EVEN).movePointRight(3)
                record.distance = distance.toLong()
            } else
                record.distance = Long.MIN_VALUE

            if (record.id == null)
                recordsListViewModel.insert(record)
            else
                recordsListViewModel.update(record)
        }
        alert.setNegativeButton(android.R.string.no) { dialog, position -> dialog?.cancel() }

        return alert.create()
    }

    private fun getTimeFromMaskedEditText(stringValue: String): Long {
        if (stringValue.isEmpty())
            return Long.MIN_VALUE

        var stringValueBuilder = StringBuilder(stringValue)

        for ((index, s) in stringValueBuilder.withIndex()) {
            if (!s.isDigit() && !s.equals(':'))
                stringValueBuilder[index] = '0'
        }

        var time: String
        time = stringValueBuilder.subSequence(0, 2).toString()

        val minutes = stringValueBuilder.subSequence(3, 5).toString()
        time += if (minutes.toLong() < 59) minutes else "59"

        val seconds = stringValueBuilder.subSequence(6, 8).toString()
        time += if (seconds.toLong() < 59) seconds else "59"

        time += stringValueBuilder.subSequence(9, 12).toString()

        return time.toLong()
    }

    private fun getTimeFromDatabaseLongValue(value: Long): String {
        if ((value == Long.MIN_VALUE) || value <= 0L)
            return ""

        var output = StringBuilder()
        val stringValue = StringBuilder(value.toString())

        if (stringValue.length < 9) {
            var len = stringValue.length
            while (len < 9) {
                output.append('0')
                len++
            }
        }

        output.append(stringValue)
        output.insert(1, ":")
        output.insert(4, ":")
        output.insert(7, ":")

        return output.toString()
    }

    private fun fieldIsBlank(text: Editable?): Boolean {
        if (text.isNullOrBlank())
            return true
        return false
    }
}