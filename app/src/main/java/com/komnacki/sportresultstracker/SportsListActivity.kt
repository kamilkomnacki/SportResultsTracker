package com.komnacki.sportresultstracker

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter

import kotlinx.android.synthetic.main.activity_sports_list.*
import kotlinx.android.synthetic.main.content_sports_list.*

class SportsListActivity : AppCompatActivity() {

    var list = ArrayList<String>()
    var list2 = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sports_list)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        list.add("Element 1")
        list.add("Element 2")
        list.add("Element 3")
        list.add("Element 4")
        list.add("Element 5")
        list.add("Element 6")
        list.add("Element 7")
        list.add("Element 8")
        list.add("Element 9")
        list.add("Element 10")
        list.add("Element 11")
        list.add("Element 12")
        list.add("Element 13")
        list.add("Element 14")
        list.add("Element 15")
        list.add("Element 16")

        lv_sports.emptyView = empty_sportsList
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list)
        lv_sports.adapter = adapter





    }

}
