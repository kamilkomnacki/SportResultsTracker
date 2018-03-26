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
import com.komnacki.sportresultstracker.database.Sport
import com.komnacki.sportresultstracker.database.SportConsts
import kotlinx.android.synthetic.main.activity_sports_list.*
import kotlinx.android.synthetic.main.alert_dialog_sport_input.view.*
import kotlinx.android.synthetic.main.alert_dialog_user_input.*


class SportsListActivity : AppCompatActivity() {

    val LOG_TAG = SportsListAdapter::class.java.name
    private lateinit var sportsListViewModel: SportsListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sports_list)
        setSupportActionBar(toolbar)

        val recyclerView: RecyclerView = findViewById(R.id.rv_sportsList)
        val emptyView: RelativeLayout = findViewById(R.id.empty_sportsList)
        val itemOnClick: (View, Int, Int, Long?) -> Unit = { recyclerView, type, position, id ->
            val intent = Intent(this, SportEditActivity::class.java)
            intent.putExtra(SportConsts.ID, id)
            startActivity(intent)
        }
        val adapter = SportsListAdapter(this, itemOnClick)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val userId = intent.getLongExtra(SportConsts.USER_ID, -1)
        Log.d(LOG_TAG, "INTENT EXTRA = " + userId)

        sportsListViewModel = ViewModelProviders
                .of(this, SportsListFactory(this.application, userId))
                .get(SportsListViewModel::class.java)
        sportsListViewModel.getSportsList().observe(this, object : Observer<List<Sport>> {
            override fun onChanged(t: List<Sport>?) {
                adapter.setSports(t)
            }
        })

        adapter.registerAdapterDataObserver(EmptyListObserver(recyclerView, emptyView))

        fab.setOnClickListener { view ->
            insertNewSport(userId)
            Toast.makeText(applicationContext, "Add new sport", Toast.LENGTH_SHORT).show()
        }
    }

    private fun insertNewSport(userId: Long){
        val alert = android.app.AlertDialog.Builder(this)
        alert.setTitle("Add new sport activity")
        val viewInflated = LayoutInflater
                            .from(this)
                            .inflate(R.layout.alert_dialog_sport_input,
                                    fl_alert_dialog_user_input,
                                    false)

        val input = viewInflated.tv_input_sport
        alert.setView(viewInflated)
        alert.setPositiveButton(android.R.string.yes) { dialog, position ->
            val sport = Sport()
            val name = input.text.toString()
            if(!isNameExist(name)) {
                sport.name = name
                sport.recordsAmount = 0
                sport.user_id = userId
                sport.hasTime = viewInflated.cb_hasTime.isChecked
                sport.hasDistance = viewInflated.cb_hasDistance.isChecked
                sport.hasRepeat = viewInflated.cb_hasRepeat.isChecked
                sport.hasWeight = viewInflated.cb_hasWeight.isChecked
                sportsListViewModel.insert(sport)
            }
        }
        alert.setNegativeButton(android.R.string.no) { dialog, position -> dialog?.cancel() }
        alert.show()
    }

    private fun isNameExist(name: String): Boolean{
        if(sportsListViewModel.isNameExist(name)) {
            Toast.makeText(this, "This name of sport is already exist!", Toast.LENGTH_SHORT).show()
            return true
        }

        return false
    }
}
