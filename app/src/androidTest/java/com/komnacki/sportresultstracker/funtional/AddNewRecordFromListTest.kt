package com.komnacki.sportresultstracker.funtional

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItem
import android.support.test.espresso.matcher.RootMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.text.InputType
import com.komnacki.sportresultstracker.R
import com.komnacki.sportresultstracker.funtional.Utils.RecyclerViewItemCountAssertion
import com.komnacki.sportresultstracker.funtional.Utils.TestUtils
import com.komnacki.sportresultstracker.usersActivity.UsersListActivity
import com.komnacki.sportresultstracker.usersActivity.UsersListAdapter
import org.hamcrest.Matchers.allOf
import org.hamcrest.core.IsNot
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class AddNewRecordFromListTest {
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
        onView(withId(R.id.rv_usersList)).check { view, noViewFoundException ->
            listCount = TestUtils.getSizeOfRecyclerView(view as RecyclerView)
        }

        while (listCount > 0) {
            onView(withId(R.id.item_usersList_btn_delete)).perform(click())

            onView(withText("Delete user"))
                    .inRoot(RootMatchers.withDecorView(IsNot.not(activityRule.activity.window.decorView)))
                    .check(ViewAssertions.matches(isDisplayed()))

            onView(withId(android.R.id.button1))
                    .perform(click())

            onView(withId(R.id.rv_usersList)).check { view, noViewFoundException ->
                listCount = TestUtils.getSizeOfRecyclerView(view as RecyclerView)
            }
        }

        onView(withId(R.id.rv_usersList))
                .check(RecyclerViewItemCountAssertion(0))

        onView(withId(R.id.fab)).perform(click())

        onView(withId(R.id.tv_input_user))
                .inRoot(RootMatchers.isDialog())
                .perform(click())
                .perform(ViewActions.typeTextIntoFocusedView(TestUtils.userName))

        onView(withId(android.R.id.button1))
                .perform(click())

        onView(withId(R.id.rv_usersList))
                .check(RecyclerViewItemCountAssertion(1))

        onView(allOf(withId(R.id.rv_usersList), isDisplayed()))
                .perform(actionOnItem<UsersListAdapter.ViewHolder>(withChild(withText(TestUtils.userName)), click()))


        //sportActivity

        onView(withId(R.id.rv_sportsList))
                .check(RecyclerViewItemCountAssertion(0))

        onView(withId(R.id.fab)).perform(click())

        onView(withId(R.id.tv_input_sport))
                .inRoot(RootMatchers.isDialog())
                .perform(click())
                .perform(ViewActions.typeTextIntoFocusedView(TestUtils.sportName))

        onView(withId(android.R.id.button1))
                .perform(click())

        onView(withId(R.id.rv_sportsList))
                .check(RecyclerViewItemCountAssertion(1))

        onView(allOf(withId(R.id.rv_sportsList), isDisplayed()))
                .perform(actionOnItem<UsersListAdapter.ViewHolder>(withChild(withText(TestUtils.sportName)), click()))

        //chart activity

        Thread.sleep(TestUtils.timeIntervalMillis)
        onView(withId(R.id.fab_toRecordsList)).perform(click())

        //record activity

        Thread.sleep(TestUtils.timeIntervalMillis)
        onView(withId(R.id.rv_recordsList))
                .check(RecyclerViewItemCountAssertion(0))

        Thread.sleep(TestUtils.timeIntervalMillis)
        onView(withId(R.id.fab)).perform(click())

        Thread.sleep(TestUtils.timeIntervalMillis)
        onView(withId(R.id.et_time_recordEdit))
                .inRoot(RootMatchers.isDialog())
                .perform(click())
                .check(ViewAssertions.matches(allOf(withInputType(InputType.TYPE_CLASS_NUMBER))))

        Thread.sleep(TestUtils.timeIntervalMillis)
        onView(withId(R.id.et_time_recordEdit))
                .inRoot(RootMatchers.isDialog())
                .perform(click())
                .perform(ViewActions.typeText("123456789"))
                .perform(ViewActions.pressBack())

        Thread.sleep(TestUtils.timeIntervalMillis)
        onView(withId(R.id.et_distance_recordEdit))
                .inRoot(RootMatchers.isDialog())
                .check(ViewAssertions.matches(isDisplayed()))
                .perform(click())
                .check(ViewAssertions.matches(allOf(withInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL + InputType.TYPE_CLASS_NUMBER))))

        Thread.sleep(TestUtils.timeIntervalMillis)
        onView(withId(R.id.et_distance_recordEdit))
                .inRoot(RootMatchers.isDialog())
                .check(ViewAssertions.matches(isDisplayed()))
                .perform(click())
                .perform(ViewActions.typeText("146"))
                .perform(ViewActions.pressBack())

        Thread.sleep(TestUtils.timeIntervalMillis)
        onView(withId(android.R.id.button1))
                .perform(click())

        onView(withId(R.id.rv_recordsList))
                .check(RecyclerViewItemCountAssertion(1))
    }
}