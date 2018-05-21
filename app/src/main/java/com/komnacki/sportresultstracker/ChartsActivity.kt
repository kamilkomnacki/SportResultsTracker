package com.komnacki.sportresultstracker

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.*
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.komnacki.sportresultstracker.database.Record
import com.komnacki.sportresultstracker.database.RecordConsts
import com.komnacki.sportresultstracker.database.SportConsts
import kotlinx.android.synthetic.main.activity_charts.*
import kotlinx.android.synthetic.main.fragment_charts.view.*

class ChartsActivity : AppCompatActivity() {

    lateinit var recordsListViewModel: RecordsListViewModel
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    var listOfRecords: List<Record>? = null
    private var sportId: Long = -1


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_charts)

        setSupportActionBar(toolbar)

        Log.d("CHARTSACTIVITY:", "onCreate")

        sportId = intent.getLongExtra(SportConsts.ID, -1);

        recordsListViewModel = ViewModelProviders
                .of(this, RecordsListFactory(this.application, sportId))
                .get(RecordsListViewModel::class.java)
        recordsListViewModel.getRecordList().observe(this, object : Observer<List<Record>>{
            override fun onChanged(t: List<Record>?) {
                Log.d("FRAGMENT size1:", t.toString())
                listOfRecords = t
                mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
                container.adapter = mSectionsPagerAdapter
            }
        })



        //listOfRecords = recordsListViewModel.getRecordList()

        //Log.d("FRAGMENT ChartsActivity:", listOfRecords.toString())


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.


        // Set up the ViewPager with the sections adapter.



        fab.setOnClickListener { view ->
            var record = Record()
            record.sport_id = sportId
            showRecordEditDialog(record)
        }
    }

    //---------------------------------------------------
    override fun onStart() {
        recordsListViewModel.getRecordList().observe(this, object : Observer<List<Record>>{
            override fun onChanged(t: List<Record>?) {
                Log.d("FRAGMENT size1:", t.toString())
                listOfRecords = t
                mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
                container.adapter = mSectionsPagerAdapter
            }
        })
        Log.d("CHARTSACTIVITY:", "onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("CHARTSACTIVITY:", "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("CHARTSACTIVITY:", "onPause")
        super.onPause()
    }

    override fun onStop() {
        recordsListViewModel.getRecordList().removeObservers(this)
        super.onStop()
    }

    //---------------------------------------------------

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_charts, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        when (id) {
            R.id.action_listOfRecords -> {
                intent = Intent(this, RecordsListActivity::class.java)
                intent.putExtra(RecordConsts.SPORT_ID, sportId)
                startActivity(intent)
            }
            R.id.action_settings -> return true
        }


        return super.onOptionsItemSelected(item)
    }

    private fun showRecordEditDialog(record: Record) {
        val RECORD_INPUT_DIALOG_TAG = RecordInputDialogFragment::class.java.name
        val recordInputDialogFragment = RecordInputDialogFragment.newInstance("Your record", record, recordsListViewModel)
        recordInputDialogFragment.show(fragmentManager, RECORD_INPUT_DIALOG_TAG)
    }

    val mLifecycleRegistry = LifecycleRegistry(this)
    override fun getLifecycle(): LifecycleRegistry {
        return mLifecycleRegistry

    }

    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {


        override fun getItem(position: Int): Fragment {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).


            if(listOfRecords == null){
                Log.d("SectionPagerAdapter:", "listOfRecords is null")
            }
            return PlaceholderFragment.newInstance(
                    position + 1,
                    listOfRecords)
        }

        override fun getCount(): Int {
            return 3
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */

    class PlaceholderFragment : Fragment() {

        companion object {

            /**
             * The fragment argument representing the section number for this
             * fragment.
             */
            private val ARG_SECTION_NUMBER = "section_number"
            private val ARG_IS_RECORDS_NULL = "is_records_null"
            private lateinit var records: List<Record>

            /**
             * Returns a new instance of this fragment for the given section
             * number.
             */
            fun newInstance(sectionNumber: Int, recFromDb: List<Record>?): PlaceholderFragment {
                val fragment = PlaceholderFragment()
                val args = Bundle()
                if(recFromDb != null) {
                    args.putBoolean(ARG_IS_RECORDS_NULL, false)
                    records = recFromDb
                }else
                    args.putBoolean(ARG_IS_RECORDS_NULL, true)


                args.putInt(ARG_SECTION_NUMBER, sectionNumber)

                fragment.arguments = args
                return fragment
            }

        }
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            val rootView = inflater.inflate(R.layout.fragment_charts, container, false)
            val pageNumber = arguments.getInt(ARG_SECTION_NUMBER)
            var chart: LineChart = rootView.lineChartView
            var lineData: LineData? = null
            when(pageNumber){
                1 -> {
                    lineData=distanceToDateChart(arguments.getBoolean(ARG_IS_RECORDS_NULL))}
            }
            chart.clear()
            if(lineData == null) {
                Log.d("F@@@:", records.toString())
                chart.clear()
            }
            else {
                chart.data = lineData
                chart.setTouchEnabled(true)
                chart.invalidate()
            }
            return rootView
        }

        private fun distanceToDateChart(isRecordsNull: Boolean): LineData? {
            var entries = ArrayList<Entry>()
            if(!isRecordsNull){
                records = records.sortedBy { it.date }
                for (rec in records){
                    if(!rec.distance!!.equals(Long.MIN_VALUE)) {
                        val x: Float = rec.date!!.time.div(24 * 60 * 60 * 1000).toFloat()
                        val y: Float = rec.distance!!.toFloat()
                        entries.add(Entry(x,y))
                    }
                }
            }

            var lineDataSet: LineDataSet
            if(entries.isEmpty())
                return null
            else
                lineDataSet = LineDataSet(entries, "Label")

            return LineData(lineDataSet)
        }
    }
}
