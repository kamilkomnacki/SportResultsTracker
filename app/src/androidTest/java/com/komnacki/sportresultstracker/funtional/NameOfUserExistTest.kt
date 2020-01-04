package com.komnacki.sportresultstracker.funtional

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.RootMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.v7.widget.RecyclerView
import com.komnacki.sportresultstracker.R
import com.komnacki.sportresultstracker.funtional.Utils.RecyclerViewItemCountAssertion
import com.komnacki.sportresultstracker.funtional.Utils.TestUtils
import com.komnacki.sportresultstracker.usersActivity.UsersListActivity
import org.hamcrest.core.IsNot
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class NameOfUserExistTest {
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
        var listCount = 0
        onView(withId(R.id.rv_usersList)).check { view, noViewFoundException ->
            listCount = TestUtils.getSizeOfRecyclerView(view as RecyclerView)
        }

        while (listCount > 0) {
            onView(withId(R.id.item_usersList_btn_delete)).perform(ViewActions.click())

            onView(withText("Delete user"))
                    .inRoot(RootMatchers.withDecorView(IsNot.not(activityRule.activity.window.decorView)))
                    .check(matches(isDisplayed()))

            onView(withId(android.R.id.button1))
                    .perform(ViewActions.click())

            onView(withId(R.id.rv_usersList)).check { view, noViewFoundException ->
                listCount = TestUtils.getSizeOfRecyclerView(view as RecyclerView)
            }
        }

        onView(withId(R.id.rv_usersList))
                .check(RecyclerViewItemCountAssertion(0))

        onView(withId(R.id.fab)).perform(ViewActions.click())

        Thread.sleep(TestUtils.timeIntervalMillis)
        onView(withId(R.id.tv_input_user))
                .inRoot(RootMatchers.isDialog())
                .check(matches(isDisplayed()))
                .perform(ViewActions.click())
                .perform(ViewActions.typeTextIntoFocusedView(TestUtils.userName))

        Thread.sleep(TestUtils.timeIntervalMillis)
        onView(withId(android.R.id.button1))
                .perform(ViewActions.click())

        Thread.sleep(TestUtils.timeIntervalMillis)
        onView(withId(R.id.fab)).perform(ViewActions.click())

        Thread.sleep(TestUtils.timeIntervalMillis)
        onView(withId(R.id.tv_input_user))
                .inRoot(RootMatchers.isDialog())
                .check(matches(isDisplayed()))
                .perform(ViewActions.click())
                .perform(ViewActions.typeTextIntoFocusedView(TestUtils.userName))

        Thread.sleep(TestUtils.timeIntervalMillis)
        onView(withId(android.R.id.button1))
                .perform(ViewActions.click())

        onView(withId(R.id.rv_usersList))
                .check(RecyclerViewItemCountAssertion(1))

//        Thread.sleep(TestUtils.timeIntervalMillis)
//        onToast("The name of user already exist!")
//                .check(matches(isDisplayed()))

        Thread.sleep(TestUtils.timeIntervalMillis)
        onView(withId(R.id.item_usersList_btn_delete)).perform(ViewActions.click())
        onView(withId(android.R.id.button1)).perform(ViewActions.click())
    }
}