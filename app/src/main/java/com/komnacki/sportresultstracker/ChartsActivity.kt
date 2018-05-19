package com.komnacki.sportresultstracker

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.komnacki.sportresultstracker.database.Record
import com.komnacki.sportresultstracker.database.RecordConsts
import com.komnacki.sportresultstracker.database.SportConsts

import kotlinx.android.synthetic.main.activity_charts.*
import kotlinx.android.synthetic.main.fragment_charts.view.*

class ChartsActivity : AppCompatActivity() {

    private lateinit var recordsListViewModel: RecordsListViewModel
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    private var sportId: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_charts)

        setSupportActionBar(toolbar)
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        container.adapter = mSectionsPagerAdapter

        sportId = intent.getLongExtra(SportConsts.ID, -1);
        Toast.makeText(this, "SportID:" + sportId, Toast.LENGTH_SHORT).show()

        recordsListViewModel = ViewModelProviders
                .of(this, RecordsListFactory(this.application, sportId))
                .get(RecordsListViewModel::class.java)

            fab.setOnClickListener { view ->
                var record = Record()
                record.sport_id = sportId
                showRecordEditDialog(record)
        }

    }


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

        when(id){
            R.id.action_listOfRecords -> {
                intent = Intent(this, RecordsListActivity::class.java)
                intent.putExtra(RecordConsts.SPORT_ID, sportId)
                startActivity(intent)
            }
            R.id.action_settings -> return true
        }


        return super.onOptionsItemSelected(item)
    }

    private fun showRecordEditDialog(record: Record){
        val RECORD_INPUT_DIALOG_TAG = RecordInputDialogFragment::class.java.name
        val recordInputDialogFragment = RecordInputDialogFragment.newInstance("Your record", record, recordsListViewModel)
        recordInputDialogFragment.show(fragmentManager, RECORD_INPUT_DIALOG_TAG)
    }


    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1)
        }

        override fun getCount(): Int {
            // Show 3 total pages.
            return 3
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    class PlaceholderFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            val rootView = inflater.inflate(R.layout.fragment_charts, container, false)
            rootView.section_label.text = getString(R.string.section_format, arguments.getInt(ARG_SECTION_NUMBER))
            //rootView.section_label.text = getString(R.string.section_format, arguments.getInt(ARG_SECTION_NUMBER))
            return rootView
        }

        companion object {
            /**
             * The fragment argument representing the section number for this
             * fragment.
             */
            private val ARG_SECTION_NUMBER = "section_number"

            /**
             * Returns a new instance of this fragment for the given section
             * number.
             */
            fun newInstance(sectionNumber: Int): PlaceholderFragment {
                val fragment = PlaceholderFragment()
                val args = Bundle()
                args.putInt(ARG_SECTION_NUMBER, sectionNumber)
                fragment.arguments = args
                return fragment
            }
        }
    }
}
