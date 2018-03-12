package com.komnacki.sportresultstracker

import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import com.komnacki.sportresultstracker.database.User
import com.komnacki.sportresultstracker.database.UserDAO
import dagger.Module


import kotlinx.android.synthetic.main.activity_sports_list.*
import kotlinx.android.synthetic.main.content_sports_list.*
import javax.inject.Inject


class SportsListActivity : AppCompatActivity() {

    @Inject
    lateinit var userDAO: UserDAO

    var list = ArrayList<String>()
    var list2 = ArrayList<User>()
    var list3 = ArrayList<User>()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sports_list)
        setSupportActionBar(toolbar)

        App.app()?.appComponent()?.inject(this)

        var u1: User = User()
        u1.name = "Komnacki"
        u1.sportsAmount = 5555
        list2.add(u1)



        fab.setOnClickListener { view ->
            Request().execute()
            val adapter = ArrayAdapter<User>(this, android.R.layout.simple_list_item_1, list3)
            lv_sports.adapter = adapter
        }
    }

    inner class Request : AsyncTask<Void, Void, User>() {
        override fun doInBackground(vararg p0: Void?): User {
            var u1: User = User()
            u1.name = "Komnacki"
            u1.sportsAmount = 5555
            userDAO.insert(u1)
            list3 = userDAO.getAll() as ArrayList<User>
            return u1
        }

        override fun onPostExecute(result: User?) {


        }
    }

}
