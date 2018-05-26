package com.komnacki.sportresultstracker

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.komnacki.sportresultstracker.database.Record
import com.komnacki.sportresultstracker.database.RecordConsts
import com.komnacki.sportresultstracker.database.SportConsts
import com.komnacki.sportresultstracker.formatters.DayAxisValueFormatter
import kotlinx.android.synthetic.main.activity_charts.*
import kotlinx.android.synthetic.main.fragment_charts.view.*

class ChartsActivity : AppCompatActivity() {

    val LOG_TAG: String = ChartsActivity::class.java.toString()

    lateinit var recordsListViewModel: RecordsListViewModel
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    var listOfRecords: List<Record>? = null
    private var sportId: Long = -1


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_charts)


        Log.d(LOG_TAG, "onCreate")

        sportId = intent.getLongExtra(SportConsts.ID, -1);

        recordsListViewModel = ViewModelProviders
                .of(this, RecordsListFactory(this.application, sportId))
                .get(RecordsListViewModel::class.java)
        recordsListViewModel.getRecordList().observe(this, object : Observer<List<Record>> {
            override fun onChanged(t: List<Record>?) {

                var currentFragment = supportFragmentManager.findFragmentById(charts_container.id)
                if (currentFragment == null) {
                    listOfRecords = null
                    mSectionsPagerAdapter = null
                    charts_container.adapter = null
                    Log.e(LOG_TAG + "on Create", "currentFragment is NULL!")
                    ///                   mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
                    //                    charts_container.adapter = mSectionsPagerAdapter
                } else {
                    listOfRecords = null
                    mSectionsPagerAdapter = null
                    charts_container.adapter = null
                    val fragTransaction = supportFragmentManager.beginTransaction()
                    fragTransaction.remove(currentFragment)
                    //                    //fragTransaction.detach(currentFragment)
                    //                    //fragTransaction.attach(currentFragment)
                    fragTransaction.commitAllowingStateLoss()

                    var somethingPopped = supportFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                    Log.d(LOG_TAG, "Something popped: $somethingPopped")
                }

                Log.d(LOG_TAG + " on create size1:", t.toString())
                listOfRecords = t
                mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
                charts_container.adapter = mSectionsPagerAdapter
            }
        })


        fab_toRecordsList.setOnClickListener { view ->
            intent = Intent(this, RecordsListActivity::class.java)
            intent.putExtra(RecordConsts.SPORT_ID, sportId)
            startActivity(intent)
        }


        fab.setOnClickListener { view ->
            var record = Record()
            record.sport_id = sportId
            showRecordEditDialog(record)
        }
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

        //odświeżanie środkowych stron (np position=1 nie działa przy powrocie z listy rekorów)
        override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
            Log.d(LOG_TAG, "Destroy Item called for position: $position!")
            super.destroyItem(container, position, `object`)
        }

        override fun instantiateItem(container: ViewGroup?, position: Int): Any {
            Log.d(LOG_TAG, " instantiateItem work. Position: $position")
            return super.instantiateItem(container, position)
//            return PlaceholderFragment.newInstance(
//                    position + 1,
//                    listOfRecords)
        }

        override fun getItem(position: Int): Fragment {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            if (listOfRecords == null) {
                Log.d("SectionPagerAdapter///:", "listOfRecords is null")
            } else {
                Log.d("SectionPagerAdapter///:", listOfRecords.toString())
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

        val LOG_TAG: String = PlaceholderFragment::class.java.toString()

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
                if (recFromDb != null) {
                    args.putBoolean(ARG_IS_RECORDS_NULL, false)
                    records = recFromDb
                } else {
                    Log.e("com.komnacki.sportresultstracker NEW INSTANCE", "rec fromDB is NULL")
                    args.putBoolean(ARG_IS_RECORDS_NULL, true)
                }
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
            when (pageNumber) {
                1 -> {
                    lineData = distanceToDateChart(arguments.getBoolean(ARG_IS_RECORDS_NULL))
                    chart = formatDistanceToDateChart(chart)
                }
                2 -> {
                    lineData = timeToDateChart(arguments.getBoolean(ARG_IS_RECORDS_NULL))
                    chart = formatTimeToDateChart(chart)
                }
                3 -> {
                    lineData = averageSpeedToDateChart(arguments.getBoolean(ARG_IS_RECORDS_NULL))
                    chart = formatAverageSpeedToDateChart(chart)
                }
            }

            chart = setChartOptions(chart)

            if (lineData == null)
                chart.clear()
            else {
                chart.data = lineData
                chart.setTouchEnabled(true)
                chart.invalidate()
            }
            return rootView
        }

        private fun formatAverageSpeedToDateChart(chart: LineChart): LineChart {
            chart.axisLeft.setLabelCount(5, true)
            chart.xAxis.valueFormatter = DayAxisValueFormatter(chart)
            chart.xAxis.setLabelCount(4, true)
            return chart
        }

        private fun formatTimeToDateChart(chart: LineChart): LineChart {
            chart.axisLeft.setLabelCount(5, true)
            chart.xAxis.valueFormatter = DayAxisValueFormatter(chart)
            chart.xAxis.setLabelCount(4, true)
            return chart
        }

        private fun formatDistanceToDateChart(chart: LineChart): LineChart {
            chart.axisLeft.setLabelCount(5, true)
            chart.xAxis.valueFormatter = DayAxisValueFormatter(chart)
            chart.xAxis.setLabelCount(4, true)
            return chart
        }

        private fun distanceToDateChart(isRecordsNull: Boolean): LineData? {
            val LABEL_TITLE = "Distance to date"
            var entries = ArrayList<Entry>()
            if (!isRecordsNull) {
                records = records.sortedBy { it.date }
                for (rec in records) {
                    if (!rec.distance!!.equals(Long.MIN_VALUE)) {
                        val x: Float = rec.date!!.time.div(24 * 60 * 60 * 1000).toFloat()
                        val y: Float = rec.distance!!.toFloat()
                        Log.d(LOG_TAG, " [1] entries try to add ($x), ($y)")
                        entries.add(Entry(x, y))
                    }
                }
            }

            var lineDataSet: LineDataSet
            return if (entries.isEmpty()) {
                Log.e(LOG_TAG, " Chart entries are null!")
                null
            } else {
                lineDataSet = LineDataSet(entries, LABEL_TITLE)
                lineDataSet = setLineDataSetOptions(lineDataSet)
                LineData(lineDataSet)
            }
        }

        private fun timeToDateChart(isRecordsNull: Boolean): LineData? {
            val LABEL_TITLE = "Time to date"
            var entries = ArrayList<Entry>()
            if (!isRecordsNull) {
                records = records.sortedBy { it.date }
                for (rec in records) {
                    if (!rec.time!!.equals(Long.MIN_VALUE)) {
                        val x: Float = rec.date!!.time.div(24 * 60 * 60 * 1000).toFloat()
                        val y: Float = rec.time!!.toFloat()
                        Log.d(LOG_TAG, " [2] entries try to add ($x), ($y)")
                        entries.add(Entry(x, y))
                    }
                }
            }

            var lineDataSet: LineDataSet
            return if (entries.isEmpty()) {
                Log.e(LOG_TAG, " Chart entries are null!")
                null
            } else {
                lineDataSet = LineDataSet(entries, LABEL_TITLE)
                lineDataSet = setLineDataSetOptions(lineDataSet)
                LineData(lineDataSet)
            }
        }

        private fun averageSpeedToDateChart(isRecordsNull: Boolean): LineData? {
            val LABEL_TITLE = "Average speed to date"
            var entries = ArrayList<Entry>()
            if (!isRecordsNull) {
                records = records.sortedBy { it.date }
                for (rec in records) {
                    if (!rec.distance!!.equals(Long.MIN_VALUE)) {
                        val x: Float = rec.date!!.time.div(24 * 60 * 60 * 1000).toFloat()
                        val y: Float = (rec.distance!!.toFloat() / rec.time!!.toFloat())
                        Log.d(LOG_TAG, " [3] entries try to add ($x), ($y)")
                        entries.add(Entry(x, y))
                    }
                }
            }

            var lineDataSet: LineDataSet
            return if (entries.isEmpty()) {
                Log.e(LOG_TAG, " Chart entries are null!")
                null
            } else {
                lineDataSet = LineDataSet(entries, LABEL_TITLE)
                lineDataSet = setLineDataSetOptions(lineDataSet)
                LineData(lineDataSet)
            }
        }

        private fun setChartOptions(chart: LineChart): LineChart {
            val LEGEND_TEXT_SIZE: Float = 20f
            val EXTRA_BOTTOM_OFFSET: Float = 15f
            val X_AXIS_TEXT_SIZE: Float = 15f
            val Y_AXIS_TEXT_SIZE: Float = 13f
            val GRANULATIRY: Float = 1f


            chart.clear()

            //Colors
            chart.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
            chart.setBorderColor(ContextCompat.getColor(context, R.color.colorPrimary))
            chart.setNoDataTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
            chart.setGridBackgroundColor(ContextCompat.getColor(context, R.color.colorGridGray))
            chart.extraBottomOffset = EXTRA_BOTTOM_OFFSET

            //legend
            chart.legend.textColor = ContextCompat.getColor(context, R.color.colorPrimary)
            chart.legend.textSize = LEGEND_TEXT_SIZE
            chart.legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
            chart.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT

            //x
            chart.xAxis.setAvoidFirstLastClipping(true)
            chart.xAxis.textColor = ContextCompat.getColor(context, R.color.colorPrimary)
            chart.xAxis.textSize = X_AXIS_TEXT_SIZE
            chart.xAxis.position = XAxis.XAxisPosition.BOTTOM
            chart.xAxis.granularity = GRANULATIRY

            //y
            chart.axisLeft.textColor = ContextCompat.getColor(context, R.color.colorPrimary)
            chart.axisLeft.textSize = Y_AXIS_TEXT_SIZE
            chart.axisRight.isEnabled = false


            return chart
        }

        private fun setLineDataSetOptions(lineDataSet: LineDataSet): LineDataSet {
            val FORM_LINE_WIDTH: Float = 150f

            lineDataSet.setColors(ContextCompat.getColor(context, R.color.colorPrimary))
            lineDataSet.setDrawValues(false)
            lineDataSet.formLineWidth = FORM_LINE_WIDTH
            return lineDataSet
        }
    }
}
