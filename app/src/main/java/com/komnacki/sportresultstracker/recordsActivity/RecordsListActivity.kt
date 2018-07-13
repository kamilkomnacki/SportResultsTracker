package com.komnacki.sportresultstracker.recordsActivity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import com.komnacki.sportresultstracker.EmptyListObserver
import com.komnacki.sportresultstracker.R
import com.komnacki.sportresultstracker.database.Record
import com.komnacki.sportresultstracker.database.RecordConsts
import kotlinx.android.synthetic.main.activity_records_list.*

class RecordsListActivity : AppCompatActivity() {

    val LOG_TAG = RecordsListActivity::class.java.name
    private lateinit var recordsListViewModel: RecordsListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_records_list)
        setSupportActionBar(toolbar)

        val sportId = intent.getLongExtra(RecordConsts.SPORT_ID, -1)
        val recyclerView: RecyclerView = findViewById(R.id.rv_recordsList)
        val emptyView: RelativeLayout = findViewById(R.id.empty_recordList)
        val itemOnClick: (View, Int, Int, Long?) -> Unit = { recyclerView, type, position, recordId ->
            showRecordEditDialog(recordsListViewModel.getRecord(recordId))
        }

        val adapter = RecordsListAdapter(this, itemOnClick)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        recordsListViewModel = ViewModelProviders
                .of(this, RecordsListFactory(this.application, sportId))
                .get(RecordsListViewModel::class.java)

        recordsListViewModel.getRecordList().observe(this, object : Observer<List<Record>>{
            override fun onChanged(t: List<Record>?) {
                adapter.setRecords(t)
            }
        })


        var listOfRecords: List<Record>? = recordsListViewModel.getRecordList().value
        Log.d("FRAGMENT--- size:", listOfRecords.toString())

        adapter.registerAdapterDataObserver(EmptyListObserver(recyclerView, emptyView))

        listOfRecords = recordsListViewModel.getRecordList().value
        Log.d("FRAGMENT--- size2:", listOfRecords.toString())

        fab.setOnClickListener {
            var record = Record()
            record.sport_id = sportId
            showRecordEditDialog(record)
        }

    }
    private fun showRecordEditDialog(record: Record){
        val RECORD_INPUT_DIALOG_TAG = RecordInputDialogFragment::class.java.name
        val recordInputDialogFragment = RecordInputDialogFragment.newInstance("Your record", record, recordsListViewModel)
        recordInputDialogFragment.show(fragmentManager, RECORD_INPUT_DIALOG_TAG)
    }
}
