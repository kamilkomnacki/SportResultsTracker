package com.komnacki.sportresultstracker


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.RelativeLayout
import android.widget.Toast
import com.komnacki.sportresultstracker.database.User
import kotlinx.android.synthetic.main.activity_users_list.*
import java.util.*


class UsersListActivity : AppCompatActivity() {

    val LOG_TAG = UsersListAdapter::class.java.name
    lateinit var usersListViewModel: UsersListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_list)
        setSupportActionBar(toolbar)

        val recyclerView: RecyclerView = findViewById(R.id.rv_usersList)
        val emptyView: RelativeLayout = findViewById(R.id.empty_usersList)
        val adapter = UsersListAdapter(this)
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
            val user = User()
            user.name = "User no. " + Random().nextInt(100000)
            user.sportsAmount = Random().nextInt(100000).toLong()
            usersListViewModel.insert(user)
            Log.d(LOG_TAG, "FAB has been clicked.")
            Toast.makeText(applicationContext, "Add new item", Toast.LENGTH_SHORT).show()
        }
    }
}
