package com.komnacki.sportresultstracker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import com.komnacki.sportresultstracker.database.SportConsts

class SportEditActivity : AppCompatActivity() {

    val LOG_TAG = SportEditActivity::class.java.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sport_edit)

        var sportId = intent.getLongExtra(SportConsts.ID, -1)
        Log.d(LOG_TAG, "Intent long extra user ID:" + sportId)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        menuInflater.inflate(R.menu.sport_edit_menu, menu)
        return true
    }
}
