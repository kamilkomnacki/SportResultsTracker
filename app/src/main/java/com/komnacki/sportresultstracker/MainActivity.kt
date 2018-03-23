package com.komnacki.sportresultstracker

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        test.setText("Test from MainActivity")

        buttonToSportsList.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, SportsListActivity::class.java)
            startActivity(intent)
        })

        buttonToUsersList.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, UsersListActivity::class.java)
            startActivity(intent)
        })

        buttonToSportEdit.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, SportEditActivity::class.java)
            startActivity(intent)
        })

        buttonToRecordEdit.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, RecordEditActivity::class.java)
            startActivity(intent)
        })
    }
}
