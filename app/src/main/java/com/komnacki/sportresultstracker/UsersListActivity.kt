package com.komnacki.sportresultstracker


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import com.komnacki.sportresultstracker.database.User
import kotlinx.android.synthetic.main.activity_users_list.*
import kotlinx.android.synthetic.main.alert_dialog_user_input.*
import kotlinx.android.synthetic.main.alert_dialog_user_input.view.*
import java.util.*


class UsersListActivity : AppCompatActivity() {

    private val LOG_TAG = UsersListAdapter::class.java.name
    lateinit var usersListViewModel: UsersListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_list)
        setSupportActionBar(toolbar)

        val recyclerView: RecyclerView = findViewById(R.id.rv_usersList)
        val emptyView: RelativeLayout = findViewById(R.id.empty_usersList)
        val itemOnClick: (View, Int, Int) -> Unit = { recyclerView, type, position ->
            val intent = Intent(this, SportsListActivity::class.java)
            startActivity(intent)
        }
        val adapter = UsersListAdapter(this, itemOnClick)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        usersListViewModel = ViewModelProviders.of(this).get(UsersListViewModel::class.java)
        usersListViewModel.getUserList().observe(this, object : Observer<List<User>> {
            override fun onChanged(t: List<User>?) {
                adapter.setUsers(t)
            }
        })

        adapter.registerAdapterDataObserver(EmptyListObserver(recyclerView, emptyView))

        fab.setOnClickListener { view ->
            insertNewUserDialog()
            Log.d(LOG_TAG, "FAB has been clicked.")
            Toast.makeText(applicationContext, "Add new item", Toast.LENGTH_SHORT).show()
        }
    }

    private fun insertNewUserDialog(){
        val alert = android.app.AlertDialog.Builder(this)
        alert.setTitle("Add new user")
        val viewInflated =
                LayoutInflater
                        .from(this)
                        .inflate(R.layout.alert_dialog_user_input,
                                fl_alert_dialog_user_input,
                                false)

        val input = viewInflated.tv_input_user
        alert.setView(viewInflated)

        alert.setPositiveButton(android.R.string.yes) { dialog, position ->
            val name = input.text.toString()
            val user = User()
            user.name = name
            user.sportsAmount = Random().nextInt(100000).toLong()
            usersListViewModel.insert(user)
        }
        alert.setNegativeButton(android.R.string.no) { dialog, position -> dialog?.cancel() }
        alert.show()
    }
}
