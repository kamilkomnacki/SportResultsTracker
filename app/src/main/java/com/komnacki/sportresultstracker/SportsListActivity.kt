package com.komnacki.sportresultstracker


import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.komnacki.sportresultstracker.database.User
import kotlinx.android.synthetic.main.activity_sports_list.*
import kotlinx.android.synthetic.main.content_sports_list.*
import java.util.*


class SportsListActivity : AppCompatActivity() {

    val LOG_TAG = SportsListAdapter::class.java.name
//    @Inject
//    lateinit var userDAO: UserDAO

    //lateinit var list: LiveData<List<User>>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sports_list)
        setSupportActionBar(toolbar)

        var users: ArrayList<User> = ArrayList()
//        var user1 = User()
//        user1.name = "Kamil"
//        user1.sportsAmount = 55
//        var user2 = User()
//        user2.name = "Kasia"
//        user2.sportsAmount = 13
//        var user3 = User()
//        user3.name = "Kaszmil"
//        user3.sportsAmount = 123456




        var sportsListViewModel: SportsListViewModel = ViewModelProviders.of(this).get(SportsListViewModel::class.java)
        //sportsListViewModel = ViewModelProviders.of(this).get(SportsListViewModel::class.java)


        var recyclerView: RecyclerView = findViewById(R.id.rv_sportsList)
        val adapter = SportsListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        sportsListViewModel.getUserList().observe(this, object : Observer<List<User>> {
            override fun onChanged(t: List<User>?) {
                adapter.setUsers(users)
            }
        })



        fab.setOnClickListener {view ->
            //object : View.OnClickListener{
              //  override fun onClick(p0: View?) {
                    var user = User()
                    user.name = "User no. " + Random().nextInt(100000)
                    user.sportsAmount = Random().nextInt(100000).toLong()
                    users.add(user)
                    sportsListViewModel.insert(user)
                    Log.d(LOG_TAG, "FAB has been clicked.")
                    Toast.makeText(applicationContext, "Przycisk", Toast.LENGTH_SHORT).show()
                //}
            //}
        }



    }
}
