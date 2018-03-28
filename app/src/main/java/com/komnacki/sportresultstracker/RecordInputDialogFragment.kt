package com.komnacki.sportresultstracker

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import com.redmadrobot.inputmask.MaskedTextChangedListener
import kotlinx.android.synthetic.main.alert_dialog_record_input.*
import kotlinx.android.synthetic.main.alert_dialog_record_input.view.*


class RecordInputDialogFragment : DialogFragment() {

    companion object {
        private val ARG_TITLE: String = "TITLE"

        fun newInstance(title: String) : RecordInputDialogFragment{
            var fragment: RecordInputDialogFragment = RecordInputDialogFragment()
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
        et_time_recordEdit.addTextChangedListener(etTime_Listener)
        et_time_recordEdit.setOnFocusChangeListener(etTime_Listener)

        alert.setView(viewInflated)
        alert.setTitle(title)



        alert.setPositiveButton(android.R.string.yes) { dialog, position ->
//            val name = viewInflated.tv_input_sport.text.toString()
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