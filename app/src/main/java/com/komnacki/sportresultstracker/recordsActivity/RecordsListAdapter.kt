package com.komnacki.sportresultstracker.recordsActivity

import android.app.AlertDialog
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.komnacki.sportresultstracker.R
import com.komnacki.sportresultstracker.database.Record
import com.komnacki.sportresultstracker.database.RecordRepository
import java.text.SimpleDateFormat

class RecordsListAdapter(
        val context: Context,
        val itemOnClick: (View, Int, Int, Long?) -> Unit)
    : RecyclerView.Adapter<RecordsListAdapter.ViewHolder>() {

    private val LOG_TAG = RecordsListAdapter::class.java.name
    private var inflater: LayoutInflater = LayoutInflater.from(context)
    var list: List<Record>? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView = inflater.inflate(R.layout.item_records_list, parent, false)
        return ViewHolder(itemView).onClick(itemOnClick)
    }

    override fun getItemCount(): Int {
        return if (list != null) list!!.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (list != null) {
            val current: Record = list!![position]

            val dateFormat = SimpleDateFormat("dd MMM yyyy")
            holder.RecordsListItemView_date.text = dateFormat.format(current.date)

            if (current.time!!.equals(Long.MIN_VALUE))
                holder.RecordListItemView_time.text = " "
            else
                holder.RecordListItemView_time.text = getFormattedTime(current.time.toString())

            if (current.distance!!.equals(Long.MIN_VALUE))
                holder.RecordListItemView_distance.text = " "
            else
                holder.RecordListItemView_distance.text = getFormattedDistance(current.distance.toString())

            holder.RecordsListItemDeleteBtn.setOnClickListener({ view ->
                deleteItem(current)
            })
        }
    }

    private fun getFormattedDistance(distance: String): String {
        return if (distance.length < 4)
            "$distance m"
        else {
            var output = StringBuilder(distance)
            output.insert(distance.length - 3, '.')
            output.append(" km").toString()
        }
    }

    private fun getFormattedTime(time: String): String {

        val stringValue = StringBuilder(time)
        var len = stringValue.length

        when (len) {
            1, 2, 3 -> {
                return stringValue.toString() + " ms"
            }
            4, 5 -> {
                stringValue.insert(len - 3, ":")
                return stringValue.toString() + " s"
            }
            6, 7 -> {
                stringValue.insert(len - 3, ":")
                stringValue.insert(len - 5, ":")
                return stringValue.toString() + " min"
            }
            8, 9 -> {
                stringValue.insert(len - 3, ":")
                stringValue.insert(len - 5, ":")
                stringValue.insert(len - 7, ":")
                return stringValue.toString() + " h"
            }
            else -> {
                return " "
            }
        }
    }

    private fun deleteItem(current: Record) {
        val alert = AlertDialog.Builder(context)
        alert.setTitle("Delete record")
        alert.setMessage("Are you sure you want to delete this record?")
        alert.setPositiveButton(android.R.string.yes) { dialog, position ->
            val recordRepository = RecordRepository()
            recordRepository.delete(current)
            notifyItemRemoved(position)
        }
        alert.setNegativeButton(android.R.string.no) { dialog, position -> dialog?.cancel() }
        alert.show()
    }

    fun setRecords(records: List<Record>?) {
        list = records!!.sortedBy { it.date }

        notifyDataSetChanged()
        Log.d(LOG_TAG, "Notify data set changed.")
    }

    fun getRecords(): List<Record>? {
        return list
    }

    fun <T : RecyclerView.ViewHolder> T.onClick(event: (view: View, type: Int, position: Int, id: Long?) -> Unit): T {
        itemView.setOnClickListener {
            val id = list!!.get(adapterPosition).id
            event.invoke(it, itemViewType, adapterPosition, id)
        }
        return this
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var RecordsListItemView_date: TextView = itemView.findViewById(R.id.item_recordList_tv_date)
        var RecordListItemView_time: TextView = itemView.findViewById(R.id.item_recordList_tv_time)
        var RecordListItemView_distance: TextView = itemView.findViewById(R.id.item_recordList_tv_distance)
        var RecordsListItemDeleteBtn: ImageButton = itemView.findViewById(R.id.item_recordList_btn_delete)
    }
}