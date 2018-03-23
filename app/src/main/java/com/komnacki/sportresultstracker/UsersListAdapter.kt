package com.komnacki.sportresultstracker

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ListView
import android.widget.RelativeLayout
import android.widget.TextView
import com.komnacki.sportresultstracker.database.User
import com.komnacki.sportresultstracker.database.UserRepository

class UsersListAdapter(context: Context) : RecyclerView.Adapter<UsersListAdapter.ViewHolder>() {

    val LOG_TAG = UsersListAdapter::class.java.name
    var inflater: LayoutInflater = LayoutInflater.from(context)
    var list: List<User>? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView = inflater.inflate(R.layout.item_users_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return if (list != null) list!!.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (list != null) {
            val current: User = list!!.get(position)
            holder.usersListItemView.text = current.name
            holder.usersListItemDeleteBtn.setOnClickListener({ view ->
                deleteItem(current)
                notifyItemRemoved(position)
            })
        }
    }

    private fun deleteItem(current: User) {
        val userRepository = UserRepository()
        userRepository.delete(current)
    }

    fun setUsers(users: List<User>?) {
        list = users
        notifyDataSetChanged()
        Log.d(LOG_TAG, "Notify data set changed.")
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var usersListItemView: TextView = itemView.findViewById(R.id.item_usersList_tv_name)
        var usersListItemDeleteBtn: ImageButton = itemView.findViewById(R.id.item_usersList_btn_delete)
    }
}
