package com.komnacki.sportresultstracker

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.komnacki.sportresultstracker.database.Record
import com.komnacki.sportresultstracker.database.RecordRepository

class RecordsListAdapter(
    val context: Context,
    val itemOnClick: (View, Int, Int, Long?) -> Unit)
    : RecyclerView.Adapter<RecordsListAdapter.ViewHolder>() {

        private val LOG_TAG = RecordsListAdapter::class.java.name
        private var inflater: LayoutInflater = LayoutInflater.from(context)
        var list: List<Record>? = null

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecordsListAdapter.ViewHolder {
            val itemView = inflater.inflate(R.layout.item_records_list, parent, false)
            return ViewHolder(itemView).onClick(itemOnClick)
        }

        override fun getItemCount(): Int {
            return if (list != null) list!!.size else 0
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            if (list != null) {
                val current: Record = list!!.get(position)
                holder.RecordsListItemView_date.text = current.date.toString()
                holder.RecordListItemView_time.text = current.time.toString()
                holder.RecordListItemView_distance.text = current.distance.toString()
                holder.RecordsListItemDeleteBtn.setOnClickListener({ view ->
                    deleteItem(current)
                    notifyItemRemoved(position)
                })
            }
        }

        private fun deleteItem(current: Record) {
            val recordRepository = RecordRepository()
            recordRepository.delete(current)
        }

        fun setRecords(records: List<Record>?) {
            list = records
            notifyDataSetChanged()
            Log.d(LOG_TAG, "Notify data set changed.")
        }

        fun<T: RecyclerView.ViewHolder> T.onClick(event: (view: View, type: Int, position: Int, id: Long?) -> Unit): T {
            itemView.setOnClickListener{
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