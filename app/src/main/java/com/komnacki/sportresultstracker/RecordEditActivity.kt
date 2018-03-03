package com.komnacki.sportresultstracker

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu

class RecordEditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_edit)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_recordedit, menu)
        return true
    }
}
