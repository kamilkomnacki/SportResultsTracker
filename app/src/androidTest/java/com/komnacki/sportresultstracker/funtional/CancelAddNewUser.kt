package com.komnacki.sportresultstracker.funtional

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.matcher.RootMatchers
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.komnacki.sportresultstracker.R
import com.komnacki.sportresultstracker.funtional.Utils.RecyclerViewItemCountAssertion
import com.komnacki.sportresultstracker.funtional.Utils.TestUtils
import com.komnacki.sportresultstracker.usersActivity.UsersListActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CancelAddNewUser {
    private lateinit var stringToBetyped: String

    @get:Rule
    var activityRule: ActivityTestRule<UsersListActivity> = ActivityTestRule(UsersListActivity::class.java)

    @Before
    fun initValidString() {
        // Specify a valid string.
        stringToBetyped = "Espresso"
    }

    @Test
    fun testIfAddNewUserDialogAppear() {
        Espresso.onView(ViewMatchers.withId(R.id.rv_usersList))
                .check(RecyclerViewItemCountAssertion(0))

        Espresso.onView(ViewMatchers.withId(R.id.fab)).perform(ViewActions.click())


        Thread.sleep(TestUtils.timeIntervalMillis)
        Espresso.onView(ViewMatchers.withId(R.id.tv_input_user))
                .inRoot(RootMatchers.isDialog())
                .perform(ViewActions.click())
                .perform(ViewActions.typeTextIntoFocusedView(TestUtils.userName))

        Thread.sleep(TestUtils.timeIntervalMillis)
        Espresso.onView(ViewMatchers.withId(android.R.id.button2))
                .perform(ViewActions.click())

        Thread.sleep(TestUtils.timeIntervalMillis)
        Espresso.onView(ViewMatchers.withId(R.id.rv_usersList))
                .check(RecyclerViewItemCountAssertion(0))
    }
}