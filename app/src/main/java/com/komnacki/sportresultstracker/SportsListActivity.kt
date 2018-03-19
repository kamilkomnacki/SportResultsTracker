package com.komnacki.sportresultstracker


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.komnacki.sportresultstracker.database.User
import kotlinx.android.synthetic.main.activity_sports_list.*
import java.util.*


class SportsListActivity : AppCompatActivity() {

    val LOG_TAG = SportsListAdapter::class.java.name
    lateinit var sportsListViewModel: SportsListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sports_list)
        setSupportActionBar(toolbar)

        val recyclerView: RecyclerView = findViewById(R.id.rv_sportsList)
        val adapter = SportsListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        sportsListViewModel = ViewModelProviders.of(this).get(SportsListViewModel::class.java)
        sportsListViewModel.getUserList().observe(this, object : Observer<List<User>> {
            override fun onChanged(t: List<User>?) {
                adapter.setUsers(t)
            }
        })

        fab.setOnClickListener { view ->
            val user = User()
            user.name = "User no. " + Random().nextInt(100000)
            user.sportsAmount = Random().nextInt(100000).toLong()
            sportsListViewModel.insert(user)
            Log.d(LOG_TAG, "FAB has been clicked.")
            Toast.makeText(applicationContext, "Add new item", Toast.LENGTH_SHORT).show()
        }
    }
}
