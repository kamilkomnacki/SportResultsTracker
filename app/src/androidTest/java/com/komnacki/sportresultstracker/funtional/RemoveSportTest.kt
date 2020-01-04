package com.komnacki.sportresultstracker.funtional

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.RootMatchers
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.komnacki.sportresultstracker.R
import com.komnacki.sportresultstracker.funtional.Utils.RecyclerViewItemCountAssertion
import com.komnacki.sportresultstracker.funtional.Utils.TestUtils
import com.komnacki.sportresultstracker.usersActivity.UsersListActivity
import com.komnacki.sportresultstracker.usersActivity.UsersListAdapter
import org.hamcrest.Matchers
import org.hamcrest.core.IsNot
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RemoveSportTest {
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

        //user activity
        var listCount = 0
        Espresso.onView(ViewMatchers.withId(R.id.rv_usersList)).check { view, noViewFoundException ->
            listCount = TestUtils.getSizeOfRecyclerView(view as RecyclerView)
        }

        while (listCount > 0) {
            Espresso.onView(ViewMatchers.withId(R.id.item_usersList_btn_delete)).perform(ViewActions.click())

            Espresso.onView(ViewMatchers.withText("Delete user"))
                    .inRoot(RootMatchers.withDecorView(IsNot.not(activityRule.activity.window.decorView)))
                    .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

            Espresso.onView(ViewMatchers.withId(android.R.id.button1))
                    .perform(ViewActions.click())

            Espresso.onView(ViewMatchers.withId(R.id.rv_usersList)).check { view, noViewFoundException ->
                listCount = TestUtils.getSizeOfRecyclerView(view as RecyclerView)
            }
        }

        Espresso.onView(ViewMatchers.withId(R.id.rv_usersList))
                .check(RecyclerViewItemCountAssertion(0))

        Espresso.onView(ViewMatchers.withId(R.id.fab)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.tv_input_user))
                .inRoot(RootMatchers.isDialog())
                .perform(ViewActions.click())
                .perform(ViewActions.typeTextIntoFocusedView(TestUtils.userName))

        Espresso.onView(ViewMatchers.withId(android.R.id.button1))
                .perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.rv_usersList))
                .check(RecyclerViewItemCountAssertion(1))

        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.rv_usersList), ViewMatchers.isDisplayed()))
                .perform(RecyclerViewActions.actionOnItem<UsersListAdapter.ViewHolder>(ViewMatchers.withChild(ViewMatchers.withText(TestUtils.userName)), ViewActions.click()))


        //sportActivity

        Thread.sleep(TestUtils.timeIntervalMillis)
        Espresso.onView(ViewMatchers.withId(R.id.rv_sportsList))
                .check(RecyclerViewItemCountAssertion(0))

        Thread.sleep(TestUtils.timeIntervalMillis)
        Espresso.onView(ViewMatchers.withId(R.id.fab)).perform(ViewActions.click())

        Thread.sleep(TestUtils.timeIntervalMillis)
        Espresso.onView(ViewMatchers.withId(R.id.tv_input_sport))
                .inRoot(RootMatchers.isDialog())
                .perform(ViewActions.click())
                .perform(ViewActions.typeTextIntoFocusedView(TestUtils.sportName))

        Thread.sleep(TestUtils.timeIntervalMillis)
        Espresso.onView(ViewMatchers.withId(android.R.id.button1))
                .perform(ViewActions.click())

        Thread.sleep(TestUtils.timeIntervalMillis)
        Espresso.onView(ViewMatchers.withId(R.id.rv_sportsList))
                .check(RecyclerViewItemCountAssertion(1))

        Thread.sleep(TestUtils.timeIntervalMillis)
        Espresso.onView(ViewMatchers.withId(R.id.item_sportsList_btn_delete)).perform(ViewActions.click())

        Thread.sleep(TestUtils.timeIntervalMillis)
        Espresso.onView(ViewMatchers.withText("Delete sport activity"))
                .inRoot(RootMatchers.withDecorView(IsNot.not(activityRule.activity.window.decorView)))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Thread.sleep(TestUtils.timeIntervalMillis)
        Espresso.onView(ViewMatchers.withId(android.R.id.button1))
                .perform(ViewActions.click())

        Thread.sleep(TestUtils.timeIntervalMillis)
        Espresso.onView(ViewMatchers.withId(R.id.ic_empty_sportsList))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.rv_sportsList))
                .check(RecyclerViewItemCountAssertion(0))
    }
}