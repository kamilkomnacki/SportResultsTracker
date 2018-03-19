package com.komnacki.sportresultstracker

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import com.komnacki.sportresultstracker.database.User

class SportsListAdapter(context: Context) : RecyclerView.Adapter<SportsListAdapter.ViewHolder>() {

    val LOG_TAG = SportsListAdapter::class.java.name
    var inflater: LayoutInflater = LayoutInflater.from(context)
    var list: List<User>? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var itemView = inflater.inflate(R.layout.item_sports_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return if (list != null) list!!.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (list != null) {
            var current: User = list!!.get(position)
            holder.sportsListItemView.text = current.name + ", " + current.sportsAmount
        } else
            holder.sportsListItemView.text = "No users..."
    }

    fun setUsers(users: List<User>?) {
        list = users
        notifyDataSetChanged()
        Log.d(LOG_TAG, "Notify data set changed.")
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var sportsListItemView: TextView = itemView.findViewById(R.id.tv_sportsList_item)
    }
}




