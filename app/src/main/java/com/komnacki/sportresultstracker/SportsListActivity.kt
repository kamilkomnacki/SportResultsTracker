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
import com.komnacki.sportresultstracker.database.Sport
import com.komnacki.sportresultstracker.database.SportConsts
import kotlinx.android.synthetic.main.activity_sports_list.*
import java.util.*


class SportsListActivity : AppCompatActivity() {

    val LOG_TAG = SportsListAdapter::class.java.name
    private lateinit var sportsListViewModel: SportsListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sports_list)
        setSupportActionBar(toolbar)

        val recyclerView: RecyclerView = findViewById(R.id.rv_sportsList)
        val emptyView: RelativeLayout = findViewById(R.id.empty_sportsList)
        val adapter = SportsListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val userId = intent.getLongExtra(SportConsts.USER_ID, 0)
        Log.d(LOG_TAG, "INTENT EXTRA = " + userId)

        sportsListViewModel = ViewModelProviders.of(this)
                .get(SportsListViewModel::class.java)
        sportsListViewModel.getSportsList().observe(this, object : Observer<List<Sport>> {
            override fun onChanged(t: List<Sport>?) {
                adapter.setSports(t)
            }
        })

        adapter.registerAdapterDataObserver(EmptyListObserver(recyclerView, emptyView))

        fab.setOnClickListener { view ->
            val sport = Sport()
            sport.name = "User no. " + Random().nextInt(100000)
            sport.recordsAmount = Random().nextInt(100000).toLong()
            sportsListViewModel.insert(sport)
            Log.d(LOG_TAG, "FAB has been clicked.")
            Toast.makeText(applicationContext, "Add new item", Toast.LENGTH_SHORT).show()
        }
    }
}
