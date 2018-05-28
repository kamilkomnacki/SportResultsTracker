package com.komnacki.sportresultstracker.sportsActivity

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
import com.komnacki.sportresultstracker.database.Sport
import com.komnacki.sportresultstracker.database.SportRepository

class SportsListAdapter(
        val context: Context,
        val itemOnClick: (View, Int, Int, Long?) -> Unit,
        val itemOnLongClick: (View, Int, Int, Long?) -> Boolean)
        : RecyclerView.Adapter<SportsListAdapter.ViewHolder>() {

    private val LOG_TAG = SportsListAdapter::class.java.name
    private var inflater: LayoutInflater = LayoutInflater.from(context)
    var list: List<Sport>? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView = inflater.inflate(R.layout.item_sports_list, parent, false)
        return ViewHolder(itemView).onLongClick(itemOnLongClick).onClick(itemOnClick)
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
            })
        }
    }

    private fun deleteItem(current: Sport) {
        val alert = AlertDialog.Builder(context)
        alert.setTitle("Delete sport activity")
        alert.setMessage("Are you sure you want to delete sport activity " + current.name)
        alert.setPositiveButton(android.R.string.yes) { dialog, position ->
            val sportRepository = SportRepository()
            sportRepository.delete(current)
            notifyItemRemoved(position)
        }
        alert.setNegativeButton(android.R.string.no) { dialog, position -> dialog?.cancel() }
        alert.show()
    }

    fun setSports(sports: List<Sport>?) {
        list = sports
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

    fun<T: RecyclerView.ViewHolder> T.onLongClick(event: (view: View, type: Int, position: Int, id: Long?) -> Boolean): T {
        itemView.setOnLongClickListener{
            val id = list!!.get(adapterPosition).id
            event.invoke(it, itemViewType, adapterPosition, id)
        }
        return this
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var sportsListItemView: TextView = itemView.findViewById(R.id.item_sportsList_tv_name)
        var sportsListItemDeleteBtn: ImageButton = itemView.findViewById(R.id.item_sportsList_btn_delete)
    }
}




