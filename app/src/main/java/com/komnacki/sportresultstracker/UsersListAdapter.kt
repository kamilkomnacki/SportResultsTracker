package com.komnacki.sportresultstracker

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.komnacki.sportresultstracker.database.User
import com.komnacki.sportresultstracker.database.UserRepository

class UsersListAdapter(
        var context: Context,
        val itemOnClick: (View, Int, Int, Long?) -> Unit,
        val itemOnLongClick: (View, Int, Int, Long?) -> Boolean)
    : RecyclerView.Adapter<UsersListAdapter.ViewHolder>() {

    private val LOG_TAG = UsersListAdapter::class.java.name
    private var inflater: LayoutInflater = LayoutInflater.from(context)
    private var list: List<User>? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView = inflater.inflate(R.layout.item_users_list, parent, false)
        return ViewHolder(itemView).onLongClick(itemOnLongClick).onClick(itemOnClick)
    }

    override fun getItemCount(): Int {
        return if (list != null) list!!.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (list != null) {
            val current: User = list!!.get(position)
            holder.usersListItemView.text = current.id.toString() + " - " + current.name
            holder.usersListItemDeleteBtn.setOnClickListener({
                deleteItem(current)
            })
        }
    }

    private fun deleteItem(current: User) {
        val alert = AlertDialog.Builder(context)
        alert.setTitle("Delete user")
        alert.setMessage("Are you sure you want to delete user: " + current.name)
        alert.setPositiveButton(android.R.string.yes) { dialog, position ->
            val userRepository = UserRepository()
            userRepository.delete(current)
            notifyItemRemoved(position)
        }
        alert.setNegativeButton(android.R.string.no) { dialog, position -> dialog?.cancel() }
        alert.show()
    }

    fun setUsers(users: List<User>?) {
        list = users
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
        var usersListItemView: TextView = itemView.findViewById(R.id.item_usersList_tv_name)
        var usersListItemDeleteBtn: ImageButton = itemView.findViewById(R.id.item_usersList_btn_delete)
    }
}
