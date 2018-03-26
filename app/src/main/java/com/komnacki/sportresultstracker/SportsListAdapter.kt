package com.komnacki.sportresultstracker

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.komnacki.sportresultstracker.database.Sport
import com.komnacki.sportresultstracker.database.SportRepository

class SportsListAdapter(context: Context) : RecyclerView.Adapter<SportsListAdapter.ViewHolder>() {

    private val LOG_TAG = SportsListAdapter::class.java.name
    private var inflater: LayoutInflater = LayoutInflater.from(context)
    var list: List<Sport>? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView = inflater.inflate(R.layout.item_sports_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return if (list != null) list!!.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (list != null) {
            val current: Sport = list!!.get(position)
            holder.sportsListItemView.text = current.name
            holder.sportsListItemDeleteBtn.setOnClickListener({ view ->
                deleteItem(current)
                notifyItemRemoved(position)
            })
        }
    }


    private fun deleteItem(current: Sport) {
        val sportRepository = SportRepository()
        sportRepository.delete(current)
    }

    fun setSports(sports: List<Sport>?) {
        list = sports
        notifyDataSetChanged()
        Log.d(LOG_TAG, "Notify data set changed.")
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var sportsListItemView: TextView = itemView.findViewById(R.id.item_sportsList_tv_name)
        var sportsListItemDeleteBtn: ImageButton = itemView.findViewById(R.id.item_sportsList_btn_delete)
    }
}




