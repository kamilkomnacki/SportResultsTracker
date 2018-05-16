package com.komnacki.sportresultstracker

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.opengl.Visibility
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.komnacki.sportresultstracker.database.*
import com.redmadrobot.inputmask.MaskedTextChangedListener
import kotlinx.android.synthetic.main.alert_dialog_record_input.*
import kotlinx.android.synthetic.main.alert_dialog_record_input.view.*
import kotlinx.android.synthetic.main.alert_dialog_sport_input.view.*
import kotlinx.android.synthetic.main.item_records_list.view.*
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit


class RecordInputDialogFragment : DialogFragment() {
    val LOG_TAG = RecordInputDialogFragment::class.java.name


    companion object {
        private val ARG_TITLE: String = "TITLE"

        private lateinit var record: Record
        private lateinit var recordsListViewModel: RecordsListViewModel

        fun newInstance(title: String, rec: Record, recListViewModel: RecordsListViewModel) : RecordInputDialogFragment{
            var fragment: RecordInputDialogFragment = RecordInputDialogFragment()
            val args = Bundle()
            args.putString(ARG_TITLE, title)
            record = rec
            recordsListViewModel = recListViewModel
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

        //Wyświetlaj tylko te pola co są zaznaczone w danym sporcie
        val calendar = viewInflated.calendarEdit
        val etTime = viewInflated.et_time_recordEdit
        val etDistance = viewInflated.et_distance_recordEdit


        if(record.id != null){
            calendar.date = record.date!!.time
            etTime.setText(record.time.toString())
            etDistance.setText(record.distance.toString())
        }



        val etTime_Listener = MaskedTextChangedListener(
                "##:##:##:###",
                true,
                etTime,
                null,
                object: MaskedTextChangedListener.ValueListener{
                    override fun onTextChanged(maskFilled: Boolean, extractedValue: String) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }
                }
        )

        //COS TU NIE DZIALALO JAK PRZYSZEDLEM... @TODO("not implemented")
        // etTime.addTextChangedListener(etTime_Listener)
        //etTime.onFocusChangeListener = etTime_Listener
        // et_time_recordEdit.addTextChangedListener(etTime_Listener)
        // et_time_recordEdit.onFocusChangeListener = etTime_Listener

        alert.setView(viewInflated)
        alert.setTitle(title)



        alert.setPositiveButton(android.R.string.yes) { dialog, position ->
            record.date = java.util.Date(123)
            record.time = viewInflated.et_time_recordEdit.text.toString().toLong()
            record.distance = viewInflated.et_distance_recordEdit.text.toString().toLong()

            Log.d(LOG_TAG, "date: " + record.date)
            Log.d(LOG_TAG, "date: " + record.date!!.time)
            Log.d(LOG_TAG, "time: " + record.time)
            Log.d(LOG_TAG, "distance: " + record.distance)

        if (record.id == null)
            recordsListViewModel.insert(record)
        else
            recordsListViewModel.update(record)
//            if(!isNameExist(name)) {
//                sport.name = viewInflated.tv_input_sport.text.toString()
//                sport.recordsAmount = sport.recordsAmount
//                sport.user_id = userId
//                sport.hasTime = viewInflated.cb_hasTime.isChecked
//                sport.hasDistance = viewInflated.cb_hasDistance.isChecked
//                sport.hasRepeat = viewInflated.cb_hasRepeat.isChecked
//                sport.hasWeight = viewInflated.cb_hasWeight.isChecked
//
//                if (sport.id == null)
//                    sportsListViewModel.insert(sport)
//                else
//                    sportsListViewModel.update(sport)
//            }
        }
        alert.setNegativeButton(android.R.string.no) { dialog, position -> dialog?.cancel() }

        return alert.create()
    }
}