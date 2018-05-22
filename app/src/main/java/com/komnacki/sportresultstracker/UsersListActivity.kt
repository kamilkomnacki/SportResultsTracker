package com.komnacki.sportresultstracker


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import com.komnacki.sportresultstracker.database.SportConsts
import com.komnacki.sportresultstracker.database.User
import kotlinx.android.synthetic.main.activity_users_list.*
import kotlinx.android.synthetic.main.alert_dialog_user_input.*
import kotlinx.android.synthetic.main.alert_dialog_user_input.view.*


class UsersListActivity : AppCompatActivity() {

    private val LOG_TAG = UsersListAdapter::class.java.name
    lateinit var usersListViewModel: UsersListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_list)
        setSupportActionBar(toolbar)

        val recyclerView: RecyclerView = findViewById(R.id.rv_usersList)
        val emptyView: RelativeLayout = findViewById(R.id.empty_usersList)
        val itemOnClick: (View, Int, Int, Long?) -> Unit = { recyclerView, type, position, userId ->
            val intent = Intent(this, SportsListActivity::class.java)
            intent.putExtra(SportConsts.USER_ID, userId)
            startActivity(intent)
        }

        val itemOnLongClick: (View, Int, Int, Long?) -> Boolean = { recyclerView, type, position, userId ->
            Unit.equals(insertNewUserDialog(userId))
        }
        val adapter = UsersListAdapter(this, itemOnClick, itemOnLongClick)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        usersListViewModel = ViewModelProviders.of(this).get(UsersListViewModel::class.java)
        usersListViewModel.getUserList().observe(this, object : Observer<List<User>> {
            override fun onChanged(t: List<User>?) {
                adapter.setUsers(t)
            }
        })

        adapter.registerAdapterDataObserver(EmptyListObserver(recyclerView, emptyView))

        fab.setOnClickListener { view ->
            insertNewUserDialog(null)
            Log.d(LOG_TAG, "FAB has been clicked.")
            Toast.makeText(applicationContext, "Add new item", Toast.LENGTH_SHORT).show()
        }
    }

    private fun insertNewUserDialog(userId: Long?){
        val user = usersListViewModel.getUser(userId)
        val alert = android.app.AlertDialog.Builder(this)

        val viewInflated =
                LayoutInflater
                        .from(this)
                        .inflate(R.layout.alert_dialog_user_input,
                                fl_alert_dialog_user_input,
                                false)
        alert.setView(viewInflated)

        if(user.id == null){
            alert.setTitle("Add new user")
        }else{
            alert.setTitle("Edit user")
            viewInflated.tv_input_user.setText(user.name)
        }

        alert.setPositiveButton(android.R.string.yes) { dialog, position ->
            val name = viewInflated.tv_input_user.text.toString()
            if(!isNameExist(name)) {
                user.name = name
                user.sportsAmount = user.sportsAmount

                if (user.id == null)
                    usersListViewModel.insert(user)
                else
                    usersListViewModel.update(user)
            }
        }
        alert.setNegativeButton(android.R.string.no) { dialog, position -> dialog?.cancel() }
        alert.show()
    }

    private fun isNameExist(name: String): Boolean{
        if(usersListViewModel.isNameExist(name)) {
            Toast.makeText(this, "This name of user already exist!", Toast.LENGTH_LONG).show()
            return true
        }
        return false
    }

    override fun onBackPressed() {
        finish()
    }
}
